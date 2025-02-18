package com.example.waffle_project.Repository;

import com.example.waffle_project.Entity.ChatBotMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatBotMessageRepository extends JpaRepository<ChatBotMessageEntity, Long> {
    @Query(value = "SELECT * FROM chat_bot_message_entity where writer = :writer", nativeQuery = true)
    List<ChatBotMessageEntity> findByWriter(@Param("writer") String writer);
}
