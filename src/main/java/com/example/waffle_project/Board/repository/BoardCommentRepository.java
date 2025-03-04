package com.example.waffle_project.Board.repository;

import com.example.waffle_project.Board.domain.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
}
