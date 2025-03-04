package com.example.waffle_project.ChatBot.controller;

import com.example.waffle_project.ChatBot.dto.ChatBotMessage;
import com.example.waffle_project.ChatBot.service.ChatBotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ChatBot-Controller", description = "챗봇 관련 API")
@RequestMapping("/chatbot")
@RestController
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping
    @Operation(summary = "챗봇 메세지 전송", description = "writer 하고 message 값만 포함해서 호출해주세요")
    public ResponseEntity<?> SendChatBot(@RequestBody ChatBotMessage chatBotMessage){
        return chatBotService.sendChatBot(chatBotMessage);
    }

    @GetMapping("/{writer}")
    @Operation(summary = "특정회원 채팅 내역 조회", description = "특정 회원에 대한 채팅 내역을 전부 조회합니다\n " +
            "SendChatBot api 호출할 때 기입한 writer 값을 넣어주세요")
    public ResponseEntity<?> getChatBotMessage(@PathVariable String writer){
        return chatBotService.getChatBotMessage(writer);
    }



}
