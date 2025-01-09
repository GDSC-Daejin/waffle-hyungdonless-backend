package com.example.waffle_project.Service;

import com.example.waffle_project.Dto.UserDto;
import com.example.waffle_project.Entity.UserEntity;
import com.example.waffle_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder hash = new BCryptPasswordEncoder();//비밀번호 저장을 위한 해쉬화 객체

    public UserDto saveUserInfo(UserDto userDto){
        userDto.setPassword(hash.encode(userDto.getPassword())); //비밀번호 해쉬화
        UserEntity userEntity = userRepository.save(userDto.toEntity());//회원정보 저장
        return userEntity.toDto(); //저장된 회원정보 dto객체 반환
    }

    public Boolean findUser(UserDto userDto){
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        if(userEntity == null){// db에 회원 정보가 없을 경우
            return false;
        }
        else{
            return true;
        }
    }

}
