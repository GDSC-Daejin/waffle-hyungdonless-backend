package com.example.waffle_project.Common.security;

public class OAuth2User implements org.springframework.security.oauth2.core.user.OAuth2User {
    private final OAuth2Response oAuth2Response;
    private final String role;

    public OAuth2User(OAuth2Response oAuth2Response, String role) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }

    @Override
    public java.util.Map<java.lang.String, java.lang.Object> getAttributes() {
        return null;
    }

    @Override
    public java.util.Collection<org.springframework.security.core.GrantedAuthority> getAuthorities() { //role값
        return null;
    }

    @Override
    public java.lang.String getName() {
        return oAuth2Response.getName();
    }

    public String getUsername(){
        return oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
    }
}
