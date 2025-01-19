package com.example.waffle_project.Service;

import com.example.waffle_project.Dto.GoogleResponse;
import com.example.waffle_project.Dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //로그인 서비스 제공자 구분
        OAuth2Response oAuth2Response = null;


        if(registrationId.equals("google")){ //제공자가 구글일 경우
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
            return null;
        } else {
            return null;
        }

    }
}
