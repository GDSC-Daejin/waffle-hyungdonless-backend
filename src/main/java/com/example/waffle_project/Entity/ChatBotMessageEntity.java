package com.example.waffle_project.Entity;

import com.example.waffle_project.Dto.ChatBotMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChatBotMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255)
    private String writer;

    @Column(length = 255)
    private String date;

    @Column(length = 255)
    private String message;

    @Column(length = 255)
    private String answer; //answer

    public ChatBotMessage toDto(){
        ChatBotMessage chatBotMessage = new ChatBotMessage();
        chatBotMessage.setWriter(this.writer);
        chatBotMessage.setAnswer(this.answer);
        chatBotMessage.setDate(this.date);
        chatBotMessage.setMessage(this.message);
        return chatBotMessage;
    }
}
