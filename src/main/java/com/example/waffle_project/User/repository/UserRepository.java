package com.example.waffle_project.User.repository;

import com.example.waffle_project.User.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String userEmail);
    void deleteByEmail(String email);

}
