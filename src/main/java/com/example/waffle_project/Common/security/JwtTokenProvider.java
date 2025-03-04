package com.example.waffle_project.Common.security;

import com.example.waffle_project.Common.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JwtTokenProvider {

    @Autowired
    private BCryptPasswordEncoder hash;

    @Autowired
    private Utility utility;


    public String GetToken(String email){
        String token;
        //token 발급 및 반환 로직
        String header = utility.encrypt(utility.getToday().plusDays(1).toString(),utility.getEncryptKey()); //만료일자는 오늘로부터 1일
        String payload = utility.encrypt(email, utility.getEncryptKey()); //이메일을 암호화한 값
        String signature = hash.encode(header + "/" + payload + "/" + utility.getTokenKey()); //header, payload, secretKey를 합쳐서 해쉬화
        token = header + "." + payload + "." + signature; //header, payload, signature를 합쳐서 토큰 생성
        return token;
    }

    public Boolean TokensIsValid(String token){ //토큰 유효성 검사
        String[] parts = token.split("\\.", 3);
        String header = utility.decrypt(parts[0], utility.getEncryptKey());
        if(utility.getToday().isBefore(LocalDate.parse(header))){ //토큰이 만료되었다면
            return false;
        }
        String payload = utility.decrypt(parts[1], utility.getEncryptKey());
        String signature = parts[2];
        if(hash.matches(header + "/" + payload + "/" + utility.getTokenKey(), signature)){
            return true;
        } else {
            return false;
        }
    }

}
