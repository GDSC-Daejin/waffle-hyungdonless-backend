package com.example.waffle_project.SMS.service;

import com.example.waffle_project.ChatBot.repository.ChatBotMessageRepository;
import com.example.waffle_project.Common.response.ResponseDto;
import com.example.waffle_project.Common.util.SmsUtil;
import com.example.waffle_project.User.dto.UserDto;
import com.example.waffle_project.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Autowired
    private ChatBotMessageRepository chatBotMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsUtil smsUtil;

    public ResponseEntity<?> sendSmsToFindEmail(UserDto userDto) {//메세지 보내는 부분
        try {
            String name = userDto.getName();
            //수신번호 형태에 맞춰 "-"을 ""로 변환
            String phoneNum = userDto.getNumber().replaceAll("-", "");

            if (userRepository.findByEmail(userDto.getEmail()) != null) { //회원이 존재할 경우
                return ResponseEntity.badRequest()
                        .body(ResponseDto.response(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다.", null));
            }

            String verificationCode = smsUtil.generateRandomCode(); //인증코드 4자리 생성
//            session.setAttribute("code", verificationCode);
//            session.setAttribute("name", userDto.getName());
//            session.setAttribute("phoneNum", phoneNum);
//            session.setAttribute("email", userDto.getEmail());
            //smsUtil.sendOne(phoneNum, verificationCode); //sms 전송 수행 코드 사용시 주석 해제
            return ResponseEntity.ok(ResponseDto.response(HttpStatus.OK, "인증번호 전송 성공", verificationCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }
    }

}
