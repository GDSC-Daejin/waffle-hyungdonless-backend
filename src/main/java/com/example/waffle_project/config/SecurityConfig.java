package com.example.waffle_project.config;

import com.example.waffle_project.Filter.LoginFilter;
import com.example.waffle_project.Service.OAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final OAuth2UserService oAuth2UserService;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
                          OAuth2UserService oAuth2UserService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ //비밀번호 해쉬화를 위한 빈 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http //csrf 비활성화
                .csrf(csrf -> csrf.disable());

        http //로그인 페이지 비활성화
                .formLogin((auth) -> auth.disable());

        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) ->
                                userInfoEndpointConfig.userService(oAuth2UserService)));

        http //httpBasic 비활성화
                .httpBasic((auth) -> auth.disable());

        http //모든 경로에 대해 인증을 요구하지 않음
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/**").permitAll());

        http //로그인 필터 등록 / UsernamePasswordAuthenticationFilter 자리에 등록
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);

        http //세션 비활성화 (JWT 사용) / 세션 사용을 완전히 막지는 않음
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http //콘텐츠 타입 보안 비활성화
                .headers(headers -> headers
                        .contentTypeOptions(content -> content.disable())  // Content-Type 제한 해제
                );



        return http.build();
    }



}
