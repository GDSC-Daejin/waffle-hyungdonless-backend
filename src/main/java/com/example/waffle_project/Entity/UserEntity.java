package com.example.waffle_project.Entity;

import com.example.waffle_project.Dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String birth;

    @Column(length = 255)
    private String number;

    @Column(length = 255)
    private String nickname;

    public UserDto toDto(){
        UserDto userDto = new UserDto();
        userDto.setEmail(this.email);
        userDto.setPassword(this.password);
        userDto.setName(this.name);
        userDto.setBirth(this.birth);
        userDto.setNumber(this.number);
        userDto.setNickname(this.nickname);
        return userDto;
    }

}
