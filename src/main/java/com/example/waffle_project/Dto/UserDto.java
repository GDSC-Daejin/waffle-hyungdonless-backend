package com.example.waffle_project.Dto;

import com.example.waffle_project.Entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @Schema(example = "test123@gmail.com")
    private String email;

    @Schema(example = "1234")
    private String password;

    @Schema(example = "홍준표")
    private String name;

    @Schema(example = "2002-07-21")
    private String birth;

    @Schema(example = "010-5291-3807")
    private String number;

    @Schema(example = "JUNE")
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
