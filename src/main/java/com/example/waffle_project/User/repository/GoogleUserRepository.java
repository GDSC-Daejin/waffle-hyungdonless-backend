package com.example.waffle_project.User.repository;

import com.example.waffle_project.User.domain.GoogleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleUserRepository extends JpaRepository<GoogleUserEntity, String> {
    GoogleUserEntity findByEmail(String email);
    GoogleUserEntity findByGoogleId(String googleId);
}
