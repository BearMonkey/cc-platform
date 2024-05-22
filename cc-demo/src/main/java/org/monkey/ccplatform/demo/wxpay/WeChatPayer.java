package org.monkey.ccplatform.demo.wxpay;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

import static com.wechat.pay.java.core.http.UrlEncoder.urlEncode;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信小程序支付组件
 *
 * @author cc
 */
@Component
@Slf4j
public class WeChatPayer {

    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 构造签名串
     *
     * @param method    {@link RequestMethod} GET,POST,PUT等
     * @param url       请求接口 /v3/certificates
     * @param timestamp 获取发起请求时的系统当前时间戳
     * @param nonceStr  随机字符串
     * @param body      请求报文主体
     * @return 待签名字符串
     */
    private static String buildSignMessage(RequestMethod method, String url, long timestamp, String nonceStr, String body) {
        return method.toString() +
                "\n" +
                url +
                "\n" +
                timestamp +
                "\n" +
                nonceStr +
                "\n" +
                body +
                "\n";
    }

    /**
     * 构建 v3 接口所需的 Authorization
     *
     * @param method    {@link RequestMethod} 请求方法
     * @param urlSuffix 可通过 WxApiType 来获取，URL挂载参数需要自行拼接
     * @param mchId     商户Id
     * @param serialNo  商户 API 证书序列号
     * @param keyPath   key.pem 证书路径
     * @param body      接口请求参数
     * @param nonceStr  随机字符库
     * @param timestamp 时间戳
     * @param authType  认证类型
     * @return {@link String} 返回 v3 所需的 Authorization
     * @throws Exception 异常信息
     */
    private static String buildAuthorization(RequestMethod method, String urlSuffix, String mchId,
                                            String serialNo, String keyPath, String body, String nonceStr,
                                            long timestamp, String authType) throws Exception {
        // 构建签名参数
        String buildSignMessage = buildSignMessage(method, urlSuffix, timestamp, nonceStr, body);
        // 获取商户私钥
        String key = getPrivateKey(keyPath);
        // 生成签名
        String signature = encryptByPrivateKey(buildSignMessage, key);
        // 根据平台规则生成请求头 authorization
        return getAuthorization(mchId, serialNo, nonceStr, String.valueOf(timestamp), signature, authType);
    }

    /**
     * 获取授权认证信息
     *
     * @param mchId     商户号
     * @param serialNo  商户API证书序列号
     * @param nonceStr  请求随机串
     * @param timestamp 时间戳
     * @param signature 签名值
     * @param authType  认证类型，目前为 WECHATPAY2-SHA256-RSA2048
     * @return 请求头 Authorization
     */
    private static String getAuthorization(String mchId, String serialNo, String nonceStr, String timestamp, String signature, String authType) {
        Map<String, String> params = new HashMap<>(5);
        params.put("mchid", mchId);
        params.put("serial_no", serialNo);
        params.put("nonce_str", nonceStr);
        params.put("timestamp", timestamp);
        params.put("signature", signature);
        return authType.concat(" ").concat(createLinkString(params, ",", false, true));
    }

    private static String createLinkString(Map<String, String> params, String connStr, boolean encode, boolean quotes) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                if (quotes) {
                    content.append(key).append("=").append('"').append(encode ? urlEncode(value) : value).append('"');
                } else {
                    content.append(key).append("=").append(encode ? urlEncode(value) : value);
                }
            } else {
                if (quotes) {
                    content.append(key).append("=").append('"').append(encode ? urlEncode(value) : value).append('"').append(connStr);
                } else {
                    content.append(key).append("=").append(encode ? urlEncode(value) : value).append(connStr);
                }
            }
        }
        return content.toString();
    }

    /**
     * 获取证书
     *
     * @param inputStream 证书文件
     * @return {@link X509Certificate} 获取证书
     */
    private static X509Certificate getCertificate(InputStream inputStream) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            cert.checkValidity();
            return cert;
        } catch (CertificateExpiredException e) {
            throw new RuntimeException("证书已过期", e);
        } catch (CertificateNotYetValidException e) {
            throw new RuntimeException("证书尚未生效", e);
        } catch (CertificateException e) {
            throw new RuntimeException("无效的证书", e);
        }
    }
    /**
     * 获取商户私钥
     *
     * @param keyPath 商户私钥证书路径
     * @return 商户私钥
     * @throws Exception 解析 key 异常
     */
    private static String getPrivateKey(String keyPath) throws Exception {
        String originalKey = FileUtil.readUtf8String(keyPath);
        String privateKey = originalKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        return getPrivateKeyStr(loadPrivateKey(privateKey));
    }


    private static String getPrivateKeyStr(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
        //return Base64.encode(privateKey.getEncoded());
    }
    /**
     * 从字符串中加载私钥
     *
     * @param privateKeyStr 私钥
     * @return {@link PrivateKey}
     * @throws Exception 异常信息
     */
    private static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 私钥签名
     *
     * @param data       需要加密的数据
     * @param privateKey 私钥
     * @return 加密后的数据
     * @throws Exception 异常信息
     */
    private static String encryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] decode = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec priPkcs8 = new PKCS8EncodedKeySpec(decode);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(priPkcs8);
        Signature signature = Signature.getInstance("SHA256WithRSA");

        signature.initSign(priKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] signed = signature.sign();
        return StrUtil.str(Base64.getEncoder().encodeToString(signed));
    }

    /**
     * V3 接口统一执行入口
     *
     * @param method    {@link RequestMethod} 请求方法
     * @param urlPrefix 域名 {@link WeChatUrl#DOMAIN_NAME}
     * @param urlSuffix uri {@link WeChatUrl}，URL挂载参数需要自行拼接
     * @param mchId     商户Id
     * @param serialNo  商户 API 证书序列号
     * @param keyPath   apiclient_key.pem 证书路径
     * @param body      接口请求参数
     * @param nonceStr  随机字符库
     * @param timestamp 时间戳
     * @param authType  认证类型
     * @param file      文件
     * @return {@link String} 请求返回的结果
     * @throws Exception 接口执行异常
     */
    public HttpResponse v3Execution(RequestMethod method, String urlPrefix, String urlSuffix,
                                    String mchId, String serialNo, String keyPath, String body,
                                    String nonceStr, long timestamp, String authType,
                                    File file) throws Exception {
        // 构建 Authorization
        String authorization = buildAuthorization(method, urlSuffix, mchId, serialNo,
                keyPath, body, nonceStr, timestamp, authType);

        if (method == RequestMethod.GET) {
            return doGet(urlPrefix.concat(urlSuffix), authorization, serialNo, null);
        } else if (method == RequestMethod.POST) {
            return doPost(urlPrefix.concat(urlSuffix), authorization, serialNo, body);
        } else if (method == RequestMethod.DELETE) {
            return doDelete(urlPrefix.concat(urlSuffix), authorization, serialNo, body);
        } else if (method == RequestMethod.OPTIONS) {
            return doUpload(urlPrefix.concat(urlSuffix), authorization, serialNo, body, file);
        }
        return null;
    }

    /**
     * @param url           请求url
     * @param authorization 授权信息
     * @param serialNumber  公钥证书序列号
     * @param jsonData      请求参数
     * @return {@link HttpResponse} 请求返回的结果
     */
    private HttpResponse doGet(String url, String authorization, String serialNumber, String jsonData) {
        return HttpRequest.post(url)
                .addHeaders(getHeaders(authorization, serialNumber))
                .body(jsonData)
                .execute();
    }
    /**
     * @param url           请求url
     * @param authorization 授权信息
     * @param serialNumber  公钥证书序列号
     * @param jsonData      请求参数
     * @return {@link HttpResponse} 请求返回的结果
     */
    private HttpResponse doPost(String url, String authorization, String serialNumber, String jsonData) {
        return HttpRequest.post(url)
                .addHeaders(getHeaders(authorization, serialNumber))
                .body(jsonData)
                .execute();
    }
    /**
     * delete 请求
     *
     * @param url           请求url
     * @param authorization 授权信息
     * @param serialNumber  公钥证书序列号
     * @param jsonData      请求参数
     * @return {@link HttpResponse} 请求返回的结果
     */
    private HttpResponse doDelete(String url, String authorization, String serialNumber, String jsonData) {
        return HttpRequest.delete(url)
                .addHeaders(getHeaders(authorization, serialNumber))
                .body(jsonData)
                .execute();
    }

    private Map<String, String> getBaseHeaders(String authorization) {
        String userAgent = String.format(
                "WeChatPay-IJPay-HttpClient/%s (%s) Java/%s",
                getClass().getPackage().getImplementationVersion(),
                "OS",
                "VERSION" == null ? "Unknown" : "VERSION");

        Map<String, String> headers = new HashMap<>(3);
        headers.put("Accept", ContentType.JSON.toString());
        headers.put("Authorization", authorization);
        headers.put("User-Agent", userAgent);
        return headers;
    }

    /**
     * @param url           请求url
     * @param authorization 授权信息
     * @param serialNumber  公钥证书序列号
     * @param jsonData      请求参数
     * @param file          上传的文件
     * @return {@link HttpResponse} 请求返回的结果
     */
    private HttpResponse doUpload(String url, String authorization, String serialNumber, String jsonData, File file) {
        return HttpRequest.post(url)
                .addHeaders(getUploadHeaders(authorization, serialNumber))
                .form("file", file)
                .form("meta", jsonData)
                .execute();
    }

    private Map<String, String> getHeaders(String authorization, String serialNumber) {
        Map<String, String> headers = getBaseHeaders(authorization);
        headers.put("Content-Type", ContentType.JSON.toString());
        if (StrUtil.isNotEmpty(serialNumber)) {
            headers.put("Wechatpay-Serial", serialNumber);
        }
        return headers;
    }

    private Map<String, String> getUploadHeaders(String authorization, String serialNumber) {
        Map<String, String> headers = getBaseHeaders(authorization);
        headers.put("Content-Type", "multipart/form-data;boundary=\"boundary\"");
        if (StrUtil.isNotEmpty(serialNumber)) {
            headers.put("Wechatpay-Serial", serialNumber);
        }
        return headers;
    }
    /**
     * 构建返回参数
     *
     * @param httpResponse {@link HttpResponse}
     * @return {@link Map}
     */
    private Map<String, Object> buildResMap(HttpResponse httpResponse) {
        Map<String, Object> map = new HashMap<>();
        String timestamp = httpResponse.header("Wechatpay-Timestamp");
        String nonceStr = httpResponse.header("Wechatpay-Nonce");
        String serialNo = httpResponse.header("Wechatpay-Serial");
        String signature = httpResponse.header("Wechatpay-Signature");
        String body = httpResponse.body();
        int status = httpResponse.getStatus();

        map.put("timestamp", timestamp);
        map.put("nonceStr", nonceStr);
        map.put("serialNumber", serialNo);
        map.put("signature", signature);
        map.put("body", body);
        map.put("status", status);

        return map;
    }

    /**
     * 构造签名串
     *
     * @param timestamp 应答时间戳
     * @param nonceStr  应答随机串
     * @param body      应答报文主体
     * @return 应答待签名字符串
     */
    private static String buildSignMessage(String timestamp, String nonceStr, String body) {
        return new StringBuilder()
                .append(timestamp)
                .append("\n")
                .append(nonceStr)
                .append("\n")
                .append(body)
                .append("\n")
                .toString();
    }

    /**
     * 验证签名
     *
     * @param signature       待验证的签名
     * @param body            应答主体
     * @param nonce           随机串
     * @param timestamp       时间戳
     * @param certInputStream 微信支付平台证书输入流
     * @return 签名结果
     * @throws Exception 异常信息
     */
    private static boolean verifySignature(String signature, String body, String nonce, String timestamp, InputStream certInputStream) throws Exception {
        String buildSignMessage = buildSignMessage(timestamp, nonce, body);
        // 获取证书
        X509Certificate certificate = getCertificate(certInputStream);
        PublicKey publicKey = certificate.getPublicKey();
        return checkByPublicKey(buildSignMessage, signature, publicKey);
    }

    /**
     * 公钥验证签名
     *
     * @param data      需要加密的数据
     * @param sign      签名
     * @param publicKey 公钥
     * @return 验证结果
     * @throws Exception 异常信息
     */
    private static boolean checkByPublicKey(String data, String sign, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign.getBytes(StandardCharsets.UTF_8)));
    }
}
