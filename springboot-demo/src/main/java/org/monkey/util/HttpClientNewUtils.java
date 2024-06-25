package org.monkey.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * http 请求工具类，使用的是 httpcomponents 的 httpclient
 *
 * @author msd
 */
@Slf4j
public class HttpClientNewUtils {

    /**
     * 最大连接数
     */
    private static final Integer MAX_CONN = 100;

    /**
     * 并发数
     */
    private static final Integer Max_PRE_ROUTE = 20;

    /**
     * 创建连接的最长时间
     */
    private static final Integer CONNECT_TIMEOUT = 30000;

    /**
     * 从连接池中获取到连接的最长时间
     */
    private static final Integer CONNECTION_REQUEST_TIMEOUT = 500;

    /**
     * 数据传输的最长时间
     */
    private static final Integer SOCKET_TIMEOUT = 40000;

    /**
     * 发送请求的客户端单例
     */
    private static CloseableHttpClient httpClient;


    /**
     * 连接池管理类
     */
    private static PoolingHttpClientConnectionManager manager;


    /**
     * 线程监控，关闭无用的连接
     */
    private static ScheduledExecutorService monitorExecutor;

    /**
     * 相当于线程锁,用于线程安全
     */
    private final static Object syncLock = new Object();

    /**
     * 对http请求进行基本设置
     *
     * @param httpRequestBase http请求
     */
    private static void setRequestConfig(HttpRequestBase httpRequestBase) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();

        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取单例
     *
     * @param url
     * @return
     */
    public static CloseableHttpClient getHttpClient(String url) {
        String hostName = url.split("/")[2];
        //System.out.println(hostName);
        int port = 80;
        if (hostName.contains(":")) {
            String[] args = hostName.split(":");
            hostName = args[0];
            port = Integer.parseInt(args[1]);
        }

        if (httpClient == null) {
            //多线程下多个线程同时调用getHttpClient容易导致重复创建httpClient对象的问题,所以加上了同步锁
            synchronized (syncLock) {
                if (httpClient == null) {
                    httpClient = createHttpClient(hostName, port);
                    //开启监控线程,对异常和空闲线程进行关闭
                    monitorExecutor = Executors.newScheduledThreadPool(1);
                    monitorExecutor.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            //关闭异常连接
                            manager.closeExpiredConnections();
                            //关闭5s空闲的连接
                            manager.closeIdleConnections(5000, TimeUnit.MILLISECONDS);
                            // logger.info("close expired and idle for over 5s connection");
                        }
                        //服务启动后10s执行，间隔为10s
                    }, 10000, 10000, TimeUnit.MILLISECONDS);
                }
            }
        }
        return httpClient;
    }

    /**
     * 根据host和port构建httpclient实例
     *
     * @param host 要访问的域名
     * @param port 要访问的端口
     * @return
     */
    public static CloseableHttpClient createHttpClient(String host, int port) {
        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            // LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainSocketFactory)
                    .register("https", new SSLConnectionSocketFactory(sc)).build();
            //直接使用默认的
            manager = new PoolingHttpClientConnectionManager(registry);
            //设置连接参数
            manager.setMaxTotal(MAX_CONN); // 最大连接数
            manager.setDefaultMaxPerRoute(Max_PRE_ROUTE); // 路由最大连接数

            HttpHost httpHost = new HttpHost(host, port);
            manager.setMaxPerRoute(new HttpRoute(httpHost), Max_PRE_ROUTE);


        } catch (Exception e) {
            e.printStackTrace();
        }
        //重试handle也可以使用默认的
        HttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler();

        return HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler).build();
    }

    /**
     * 设置post请求的参数
     *
     * @param httpPost
     * @param params
     */
    private static void setPostParams(HttpPost httpPost, Map<String, String> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET请求，超时时间必须设置
     *
     * @param url
     * @param params Map<String, String>数据格式传参
     * @return
     */
    public static String get(String url, Map<String, String> params, String token) {
        String result = null;
        HttpClient httpClient = null;
        HttpGet httpGet = null;

        try {
            List<NameValuePair> pairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            URI uri = new URI(url + "?" + URLEncodedUtils.format(pairs, Consts.UTF_8));

            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(uri);


            httpGet.addHeader("Authorization", "Bearer" + token);
// 连接超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
// 读取超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);


            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, Consts.UTF_8);
            }
        } catch (Exception e) {
            log.error("Http get请求出现异常", e);
            e.printStackTrace();
        } finally {
            // 终止本次请求
            if (httpGet != null) {
                httpGet.abort();
                httpGet.releaseConnection();
            }
            // 释放连接
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }

        return result;
    }

    /**
     * post 表单
     *
     * @param url    目标地址
     * @param params 参数
     * @return 响应
     */
    public static String doPost(String url, Map<String, String> params) {
        //初始化
        HttpPost httpPost = new HttpPost(url);
        //配置信息
        setRequestConfig(httpPost);
        //参数信息
        setPostParams(httpPost, params);

        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            //发送请求，如果是第一次，会创建httpclient单例
            response = getHttpClient(url).execute(httpPost, HttpClientContext.create());

            //响应码为失败
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.info("Method failed: " + response.getStatusLine());
                log.info("Http服务链路异常:服务器状态码为" + response.getStatusLine().getStatusCode());
                return response.getStatusLine().toString();

            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                in = entity.getContent();
                return IOUtils.toString(in, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Http post请求出现异常", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post json
     *
     * @param url  目标
     * @param json json字符串
     * @return 结果
     */
    public static String postJson(String url, String json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return doPostJson(httpPost, url, json, null);
    }

    /**
     * post json
     *
     * @param url  目标
     * @param json json字符串
     * @return 结果
     */
    public static String postJson(String url, String json, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return doPostJson(httpPost, url, json, headers);
    }

    /**
     * put json
     *
     * @param url  目标
     * @param json json字符串
     * @return 结果
     */
    public static String putJson(String url, String json) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        return doPostJson(httpPut, url, json, null);
    }

    /**
     * put json
     *
     * @param url  目标
     * @param json json字符串
     * @return 结果
     */
    public static String putJson(String url, String json, Map<String, String> headers) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        return doPostJson(httpPut, url, json, headers);
    }


    /**
     * send json
     *
     * @param requestBase 目标
     * @param json        json字符串
     * @return 结果
     */
    public static String doPostJson(HttpEntityEnclosingRequestBase requestBase,
                                    String url, String json, Map<String, String> headers) throws IOException {

        // HttpEntityEnclosingRequestBase requestBase = new HttpPost(url);
        //配置信息
        setRequestConfig(requestBase);
        //头信息
        requestBase.addHeader("Content-type", "application/json");

        CloseableHttpResponse response = null;
        InputStream in = null;


        try {
            //json参数
            requestBase.setEntity(new StringEntity(json, StandardCharsets.UTF_8));

            if (headers != null) {
                headers.forEach(requestBase::addHeader);
            }

            response = getHttpClient(url).execute(requestBase, HttpClientContext.create());

            //响应码为失败
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.info("Method failed: " + response.getStatusLine());
                log.info("Http服务链路异常:服务器状态码为" + response.getStatusLine().getStatusCode());
                return response.getStatusLine().toString();
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                in = entity.getContent();
                return IOUtils.toString(in, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Http post json请求出现异常", e);
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * post xml
     *
     * @param url  目标
     * @param xml xml字符串
     * @return 结果
     */
    public static String postXml(String url, String xml) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return doPostXml(httpPost, url, xml, null);
    }

    /**
     * post xml
     *
     * @param url  目标
     * @param xml xml字符串
     * @return 结果
     */
    public static String postXml(String url, String xml, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return doPostXml(httpPost, url, xml, headers);
    }

    /**
     * send xml
     *
     * @param requestBase 目标
     * @param json        json字符串
     * @return 结果
     */
    public static String doPostXml(HttpEntityEnclosingRequestBase requestBase,
                                    String url, String json, Map<String, String> headers) throws IOException {

        // HttpEntityEnclosingRequestBase requestBase = new HttpPost(url);
        //配置信息
        setRequestConfig(requestBase);
        //头信息
        requestBase.addHeader("Content-type", "text/xml");

        CloseableHttpResponse response = null;
        InputStream in = null;


        try {
            //json参数
            requestBase.setEntity(new StringEntity(json, StandardCharsets.UTF_8));

            if (headers != null) {
                headers.forEach(requestBase::addHeader);
            }

            response = getHttpClient(url).execute(requestBase, HttpClientContext.create());

            //响应码为失败
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.info("Method failed: " + response.getStatusLine());
                log.info("Http服务链路异常:服务器状态码为" + response.getStatusLine().getStatusCode());
                return response.getStatusLine().toString();
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                in = entity.getContent();
                return IOUtils.toString(in, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Http post xml请求出现异常", e);
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post 单文件
     *
     * @param url  目标
     * @param file 文件
     * @return 结果
     */
    public static String doPostFile(String url, File file) {

        HttpPost httpPost = new HttpPost(url);
        //配置信息
        setRequestConfig(httpPost);

        CloseableHttpResponse response = null;
        InputStream in = null;


        try {
            //文件参数
            httpPost.setEntity(new FileEntity(file));

            response = getHttpClient(url).execute(httpPost, HttpClientContext.create());

            //响应码为失败
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.info("Method failed: " + response.getStatusLine());
                log.info("Http服务链路异常:服务器状态码为" + response.getStatusLine().getStatusCode());
                return response.getStatusLine().toString();
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                in = entity.getContent();
                return IOUtils.toString(in, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        return doGet(url, null);
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, Object> map, Map<String, String> headers) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return doGet(uriBuilder.build().toString(), headers);
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, String> headers) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        //配置信息
        setRequestConfig(httpGet);

        CloseableHttpResponse response = null;


        try {
            //其他header
            if (headers != null) {
                headers.forEach(httpGet::addHeader);
            }

            // 发起请求
            response = getHttpClient(url).execute(httpGet, HttpClientContext.create());

            // 判断状态码是否为200
            //响应码为失败
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.info("Method failed: " + response.getStatusLine());
                log.info("Http服务链路异常:服务器状态码为" + response.getStatusLine().getStatusCode());
                return response.getStatusLine().toString();
            }

            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * post 单文件
     *
     * @param url  目标
     * @return 结果
     */
    public static InputStream doGetStream(String url) {

        HttpGet httpGet = new HttpGet(url);
        //配置信息
        setRequestConfig(httpGet);

        CloseableHttpResponse response = null;

        try {
            response = getHttpClient(url).execute(httpGet, HttpClientContext.create());

            //响应码为失败
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.info("Method failed: " + response.getStatusLine());
                log.info("Http服务链路异常:服务器状态码为" + response.getStatusLine().getStatusCode());
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return entity.getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 关闭连接池
     */
    public static void closeConnectionPool() {
        try {
            httpClient.close();
            manager.close();
            monitorExecutor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
