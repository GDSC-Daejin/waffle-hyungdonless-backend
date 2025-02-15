package com.example.waffle_project.Repository;

import com.example.waffle_project.Entity.GoogleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleUserRepository extends JpaRepository<GoogleUserEntity, String> {
    GoogleUserEntity findByEmail(String email);
    GoogleUserEntity findByGoogleId(String googleId);
}
