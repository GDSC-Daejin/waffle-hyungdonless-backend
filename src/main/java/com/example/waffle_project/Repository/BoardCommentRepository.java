package com.example.waffle_project.Repository;

import com.example.waffle_project.Entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
}
