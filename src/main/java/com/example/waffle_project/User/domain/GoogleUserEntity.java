package com.example.waffle_project.User.domain;

import com.example.waffle_project.User.dto.GoogleUserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GoogleUserEntity {

    @Id
    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String googleId;

    @Column(length = 255)
    private String date;

    public GoogleUserDto toDto(){
        GoogleUserDto googleUserDto = new GoogleUserDto();
        googleUserDto.setEmail(this.email);
        googleUserDto.setName(this.name);
        googleUserDto.setGoogleId(this.googleId);
        googleUserDto.setDate(this.date);
        return googleUserDto;
    }
}
