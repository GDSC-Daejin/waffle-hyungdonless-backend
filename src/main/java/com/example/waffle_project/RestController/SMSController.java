package com.example.waffle_project.RestController;

import com.example.waffle_project.Dto.UserDto;
import com.example.waffle_project.Service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SMS-Controller", description = "휴대폰 SMS 인증 API")
@RestController
@RequestMapping("/sms")
public class SMSController {
    @Autowired
    private Service service;

    @Operation(summary = "메세지 인증 코드 전송 api", description = "해당 유저의 전화번호로 4자리 인증코드 발송 / email과 number값만 포함하여 호출하세요")
    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody UserDto userDto){
        return service.sendSmsToFindEmail(userDto);
    }
}
