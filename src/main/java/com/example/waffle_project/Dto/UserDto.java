package com.example.waffle_project.Dto;

import com.example.waffle_project.Entity.UserEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String birth;
    private String number;
    private String nickname;

    public UserEntity toEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        userEntity.setName(this.name);
        userEntity.setBirth(this.birth);
        userEntity.setNumber(this.number);
        userEntity.setNickname(this.nickname);
        return userEntity;
    }
}
