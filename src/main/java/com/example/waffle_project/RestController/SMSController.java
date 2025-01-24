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

    @Operation(summary = "메세지 인증 코드 전송 api", description = "입력값으로 userdto를 받아 해당 유저의 전화번호로 4자리 인증코드 발송 " +
            "\n/전송 실패 시 400에러 반환(ex:이미 해당 이메일로 회원이 존재하는 경우)" +
            "\n/데이터 전송 예시 : " +
            "\n {\n" +
            "        \"email\": \"test12@gmal.com\",\n" +
            "        \"password\": \"\",\n" +
            "        \"name\": \"홍준표\",\n" +
            "        \"birth\": \"\",\n" +
            "        \"number\": \"010-5291-3807\",\n" +
            "        \"nickname\": \"\"\n" +
            "    }")
    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody UserDto userDto){
        return service.sendSmsToFindEmail(userDto);
    }
}
