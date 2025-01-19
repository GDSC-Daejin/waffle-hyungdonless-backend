package com.example.waffle_project.Dto;

public interface OAuth2Response {
    String getProvider(); //제공자 이름 naver, google
    String getProviderId(); //제공자가 user에게 부여하는 id
    String getEmail();
    String getName();
}
