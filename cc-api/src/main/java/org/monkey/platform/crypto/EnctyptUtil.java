package org.monkey.platform.crypto;

import org.monkey.platform.api.exception.CommException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * EnctyptUtil
 *
 * @author cc
 * @since 2024/6/1 17:05
 */
public class EnctyptUtil {
    public static final String HMAC_SHA256 = "HmacSHA256";
    /**
     * @param plainStr 需要加密的字符串
     * @param key 秘钥
     * @return 加密后的字符串
     */
    public static String hmacsha256(String plainStr, String key) throws CommException {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
        Mac mac = null;
        try {
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to calculate " + HMAC_SHA256, e);
        }

        byte[] digest = mac.doFinal(plainStr.getBytes(StandardCharsets.UTF_8));

        // Convert result into a hexadecimal string
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
