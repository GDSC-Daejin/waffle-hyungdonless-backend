package com.example.waffle_project.Comment.repository;

import com.example.waffle_project.Comment.domain.CommentIsLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentIsLikeRepository extends JpaRepository<CommentIsLikeEntity, Long> {
}
