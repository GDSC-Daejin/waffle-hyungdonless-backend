package com.example.waffle_project.Service;

import com.example.waffle_project.Dto.UserDto;
import com.example.waffle_project.Entity.UserEntity;
import com.example.waffle_project.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder hash = new BCryptPasswordEncoder();//비밀번호 저장을 위한 해쉬화 객체

    public Boolean saveUserInfo(UserDto userDto){
        userDto.setPassword(hash.encode(userDto.getPassword())); //비밀번호 해쉬화

        try{
            UserEntity userEntity = userRepository.save(userDto.toEntity());//회원정보 저장
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public UserDto userFind(String email) {
        UserEntity userEntity = userRepository.findByEmail(email); //email로 회원정보 조회
        if (userEntity == null) { //회원정보가 없을 경우
            return null;
        } else { //회원정보가 있을 경우
            return userEntity.toDto();
        }
    }

    public List<UserDto> userFindAll(){
        List<UserEntity> userEntityList = userRepository.findAll(); //모든 회원정보 조회
        if(userEntityList.isEmpty()){//회원정보가 없을 경우
            return null;
        } else { //회원정보가 존재할 경우
            List<UserDto> userDtoList = userEntityList.stream().map(UserEntity::toDto).toList();//엔티티 리스트를 dto리스트로 변환
            return userDtoList;
        }
    }

    public Boolean userLogin(UserDto userDto){
        UserDto userDto1 = userRepository.findByEmail(userDto.getEmail()).toDto();
        if(userDto1 == null){ //해당 이메일로 조회된 회원이 없다면
            return false;
        } else {
            if(hash.encode(userDto.getPassword()).equals(userDto1.getPassword())){ //패스워드가 일치한다면
                return true;
            } else { //패스워드가 일치하지 않는다면
                return false;
            }
        }
    }

    @Transactional
    public UserDto userDelete(String email) {
        UserEntity userEntity = userRepository.findByEmail(email); //email로 회원정보 조회

        if (userEntity == null) {//회원정보가 없을 경우
            return null;
        } else { //회원정보가 있을 경우


            try{
                userRepository.deleteByEmail(email); //해당 이메일로 회원정보 삭제
                return userEntity.toDto();
            } catch (Exception e) {
                return null; //삭제에 실패했을 경우 null반환
            }


        }
    }
}
