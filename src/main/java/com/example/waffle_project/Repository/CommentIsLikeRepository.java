package com.example.waffle_project.Repository;

import com.example.waffle_project.Entity.CommentIsLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentIsLikeRepository extends JpaRepository<CommentIsLikeEntity, Long> {
}
