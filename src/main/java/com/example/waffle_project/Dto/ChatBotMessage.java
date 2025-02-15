package com.example.waffle_project.Dto;

import com.example.waffle_project.Entity.ChatBotMessageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatBotMessage {

    @Schema(example = "nickname")
    private String writer;

    @Schema(hidden = true)
    private String date;

    @Schema(example = "사부작 서비스에 대해 알려줘!")
    private String message;

    @Schema(hidden = true)
    private String answer;

    public ChatBotMessageEntity toEntity(){
        ChatBotMessageEntity chatBotMessageEntity = new ChatBotMessageEntity();
        chatBotMessageEntity.setWriter(this.writer);
        chatBotMessageEntity.setAnswer(this.answer);
        chatBotMessageEntity.setDate(this.date);
        chatBotMessageEntity.setMessage(this.message);
        return chatBotMessageEntity;
    }
}
