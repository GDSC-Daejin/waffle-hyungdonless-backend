package com.example.waffle_project.RestController;

import com.example.waffle_project.Dto.UserDto;
import com.example.waffle_project.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private Service service;

    //회원가입
    @PostMapping
    public ResponseEntity<?> userSignUp(@RequestBody UserDto userDto){
        if(service.saveUserInfo(userDto)){
            return ResponseEntity.ok(userDto); //회원가입 성공 시 userDto 반환
        }
        else{
            return ResponseEntity.badRequest().body(userDto); //회원가입 실패 시 userDto 반환
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserDto userDto){
        if(service.userLogin(userDto)){
            return ResponseEntity.ok(userDto); //로그인 성공 시 userDto 반환
        } else {
            return ResponseEntity.badRequest().body(userDto); //로그인 실패 시 userDto 반환
        }
    }

    //특정회원조회
    @GetMapping("/{email}")
    public ResponseEntity<?> userFind(@PathVariable String email){ //특정 회원 조회
        UserDto userDto = service.userFind(email); //email로 회원정보 조회 / dto반환
        if(userDto == null){
            return ResponseEntity.badRequest().body(null); //회원정보가 없을 경우
        } else {
            return ResponseEntity.ok(userDto); //회원정보가 있을 경우 dto반환
        }

    }

    //특정회원삭제
    @DeleteMapping("/{email}")
    public ResponseEntity<?> userDelete(@PathVariable String email){
        UserDto userDto = service.userDelete(email); //email로 회원정보 삭제
        if(userDto == null){
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(userDto);
        }
    }

    //모든회원조회
    @GetMapping
    public ResponseEntity<?> userFindAll(){
        if(service.userFindAll() == null){
            return ResponseEntity.badRequest().build(); //회원정보가 없을 경우
        } else {
            return ResponseEntity.ok(service.userFindAll()); //회원정보가 있을 경우 dto리스트 반환
        }
    }



}
