package com.example.waffle_project.Utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

@Getter
@Setter
@Service
public class Utility {
    private String encryptKey = "jK9#mN2$pL5@Qx8&";
    private String tokenKey = "HyUgDw9#mK$pL2@vN4";
    LocalDate today = LocalDate.now();

    public String encrypt(String text, String key) { //문자열 암호화
        try {
            key = String.format("%-16s", key).substring(0, 16);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(text.getBytes());
            // URL Safe Base64 사용 (일반 Base64 대신)
            return Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted);

        } catch (Exception e) {
            return "error";
        }
    }

    public String decrypt(String encryptedText, String key) { //문자열 복호화
        try {
            key = String.format("%-16s", key).substring(0, 16);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // URL Safe Base64 디코딩 사용
            byte[] decrypted = cipher.doFinal(Base64.getUrlDecoder().decode(encryptedText));
            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (Exception e) {
            return "error";
        }
    }
}
