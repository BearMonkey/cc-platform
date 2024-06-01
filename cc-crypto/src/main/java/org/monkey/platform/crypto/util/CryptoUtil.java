package org.monkey.platform.crypto.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * CryptoUtil
 *
 * @author cc
 * @since 2024/5/27 16:27
 */
public class CryptoUtil {
    public static void main(String[] args) throws Exception {
        String keyAlgorithm = "AES";
        int keyStrength = 256;
        String cipherTransformation = "AES/GCM/NoPadding";

        // 生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
        keyGenerator.init(keyStrength);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        // 需要加密的数据
        String plaintext = "Hello, World!";
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

        // 加密
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = encrypt(cipher, plaintextBytes, keyBytes);

        // 解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = decrypt(cipher, encryptedBytes, keyBytes);

        // 输出结果
        System.out.println("Original Text: " + plaintext);
        System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(encryptedBytes));
        System.out.println("Decrypted Text: " + new String(decryptedBytes, StandardCharsets.UTF_8));
    }

    private static byte[] encrypt(Cipher cipher, byte[] plaintext, byte[] keyBytes) throws Exception {
        byte[] iv = cipher.getParameters().getParameterSpec(GCMParameterSpec.class).getIV();
        byte[] encryptedBytes = cipher.doFinal(plaintext);
        byte[] result = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, result, iv.length, encryptedBytes.length);
        return result;
    }

    private static byte[] decrypt(Cipher cipher, byte[] encryptedBytes, byte[] keyBytes) throws Exception {
        GCMParameterSpec spec = new GCMParameterSpec(128, encryptedBytes, 0, 12);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), spec);
        return cipher.doFinal(encryptedBytes, 12, encryptedBytes.length - 12);
    }
}
