package com.example.waffle_project.Repository;

import com.example.waffle_project.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
