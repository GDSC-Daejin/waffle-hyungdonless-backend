package com.example.waffle_project.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api1")
    public ResponseEntity<?> api1(){
        return ResponseEntity.ok("api1 입니다");
    }

    @GetMapping("/api2")
    public ResponseEntity<?> api2(){
        return ResponseEntity.ok("api2 입니다");
    }

    @GetMapping("/user")
    public ResponseEntity<?> user(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userDetails.getUsername());
    }
}
