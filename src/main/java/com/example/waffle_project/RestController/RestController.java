package com.example.waffle_project.RestController;

import com.example.waffle_project.Dto.UserDto;
import com.example.waffle_project.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*")
public class RestController {
    @Autowired
    private Service service;

    @PostMapping("/register")
    public UserDto register(UserDto userDto){
        return service.saveUserInfo(userDto); //회원정보 저장 후 객체 반환
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(UserDto userDto){
        if(service.findUser(userDto)){//회원정보 찾기
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.ok("로그인 실패");
        }
    }

}
