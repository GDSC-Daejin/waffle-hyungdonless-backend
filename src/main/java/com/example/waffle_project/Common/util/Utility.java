package com.example.waffle_project.Common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Getter
@Setter
@Service
public class Utility {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String encryptKey = "jK9#mN2$pL5@Qx8&";
    private String tokenKey = "HyUgDw9#mK$pL2@vN4";
    LocalDate today = LocalDate.now();
    String todayANDtime = now.format(formatter);


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

    public String getClientIpv4(HttpServletRequest request) {
        String[] headerNames = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        String ip = null;
        // 여러 헤더에서 IP 확인
        for (String headerName : headerNames) {
            ip = request.getHeader(headerName);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                // 쉼표로 구분된 경우 첫 번째 IP 사용
                ip = ip.split(",")[0].trim();
                break;
            }
        }
        // 헤더에서 IP를 찾지 못한 경우 getRemoteAddr() 사용
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        // IPv4 형식 검증
        if (ip != null && ip.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")) {
            return ip;
        }
        // IPv4가 아닌 경우 로컬호스트 IP 반환
        return "127.0.0.1";
    }
}
