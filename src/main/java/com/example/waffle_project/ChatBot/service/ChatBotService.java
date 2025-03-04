package com.example.waffle_project.ChatBot.service;

import com.example.waffle_project.ChatBot.domain.ChatBotMessageEntity;
import com.example.waffle_project.ChatBot.dto.ChatBotMessage;
import com.example.waffle_project.ChatBot.repository.ChatBotMessageRepository;
import com.example.waffle_project.Common.util.ChatBotPrompt;
import com.example.waffle_project.Common.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatBotService {
    private final AnthropicChatModel chatModel; //챗봇 관련 객체

    @Autowired
    private Utility utility;

    @Autowired
    public ChatBotService(AnthropicChatModel chatModel) { //챗봇 생성자 주입
        this.chatModel = chatModel;
    }

    @Autowired
    private ChatBotMessageRepository chatBotMessageRepository;


    public ResponseEntity<?> sendChatBot(ChatBotMessage chatBotMessage){
        Map<String, String> response = new HashMap<>();
        if(StringUtils.isBlank(chatBotMessage.getMessage()) || chatBotMessage.getMessage().length() > 30){ //질문 예외처리
            response.put("response", "질문이 올바르지 않습니다! 30자 이내로 입력해주세요.");
            response.put("status", HttpStatus.OK.toString());
            return ResponseEntity.ok(response);
        } else {
            try{ //응답에 실패했을 경우 예외처리
                String responseMessage = chatModel.call(ChatBotPrompt.PROMPT + "\n질문 : " + chatBotMessage.getMessage());
                response.put("response", responseMessage);
                response.put("status", HttpStatus.OK.toString());

                //챗봇 질문 메세지 저장
                chatBotMessage.setDate(utility.getTodayANDtime()); //날짜 저장
                chatBotMessage.setAnswer(responseMessage); //응답 저장
                chatBotMessageRepository.save(chatBotMessage.toEntity());

                return ResponseEntity.ok(response);
            } catch (Exception e){
                response.put("error", e.getMessage());
                response.put("message", "api키가 유효하지 않을 수 있습니다.");
                response.put("status", HttpStatus.BAD_REQUEST.toString());
                return ResponseEntity.badRequest().body(response);
            }
        }
    }


    public ResponseEntity<?> getChatBotMessage(String writer){
        Map<String, String> response = new HashMap<>();
        List<ChatBotMessage> chatBotMessageList = chatBotMessageRepository.findByWriter(writer).stream().map(ChatBotMessageEntity::toDto).toList();
        if(chatBotMessageList.isEmpty()){
            response.put("error", "조회된 데이터가 없거나 잘못된 writer 값입니다.");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.badRequest().body(response);
        } else {
            return ResponseEntity.ok(chatBotMessageList);
        }
    }

}
