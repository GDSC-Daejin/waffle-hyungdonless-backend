package com.example.waffle_project.User.controller;

import com.example.waffle_project.User.dto.UserDto;
import com.example.waffle_project.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User-Controller", description = "회원 관리 API")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //회원가입
    @Operation(summary = "회원가입", description = "유저 정보를 등록하고 성공 시 userDto 반환")
    @PostMapping("/signup")
    public ResponseEntity<?> userSignUp(@RequestBody UserDto userDto){
        return userService.saveUserInfo(userDto); //회원가입
    }

    //로그인
    @Operation(summary = "로그인", description = "유저 정보를 조회하고 성공 시 token 반환")
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserDto userDto, HttpServletRequest request){
        return userService.userLogin(userDto, request); //로그인
    }

    //특정회원조회
    @Operation(summary = "특정회원조회", description = "특정 회원의 정보를 조회하고 성공 시 userDto 반환")
    @GetMapping("/{email}")
    public ResponseEntity<?> userFind(@PathVariable String email){ //특정 회원 조회
        return userService.userFind(email); //email로 회원정보 조회
    }

    //특정회원정보업데이트
    @Operation(summary = "특정회원정보업데이트", description = "특정 회원의 정보를 업데이트하고 성공 시 반영된 userDto 반환")
    @PutMapping("/{email}")
    public ResponseEntity<?> userUpdate(@PathVariable String email, @RequestBody UserDto userDto){
        return userService.userUpdate(userDto); //회원정보 업데이트
    }

    //특정회원삭제
    @Operation(summary = "특정회원삭제", description = "특정 회원의 정보를 삭제하고 성공 시 삭제된 userDto 반환")
    @DeleteMapping("/{email}")
    public ResponseEntity<?> userDelete(@PathVariable String email){
        return userService.userDelete(email); //email로 회원정보 삭제
    }

    //모든회원조회
    @Operation(summary = "모든회원조회", description = "모든 회원의 정보를 조회하고 성공 시 userDto 리스트 반환")
    @GetMapping
    public ResponseEntity<?> userFindAll(){
        return userService.userFindAll(); //회원정보가 있을 경우 dto리스트 반환
    }




}
