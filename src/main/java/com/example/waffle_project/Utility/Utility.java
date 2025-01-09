package com.example.waffle_project.Utility;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Utility {
    public String encrypt(String text, String key) { //문자열 암호화
        try {
            key = String.format("%-16s", key).substring(0, 16); //암호 문자열을 16자리로 맞춤

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            return "error";
        }
    }


    public String decrypt(String encryptedText, String key) { //문자열 복호화
        try {
            // 16자리 키로 맞추기
            key = String.format("%-16s", key).substring(0, 16);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (Exception e) {
            return "error";
        }
    }
}
