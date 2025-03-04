package com.example.waffle_project.Board.repository;

import com.example.waffle_project.Board.domain.BoardIsLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardIsLikeRepository extends JpaRepository<BoardIsLikeEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM board_is_like_entity WHERE board_id = :boardId AND email = :email", nativeQuery = true)
    void DeleteByBoardIdAndEmail(@Param("boardId") Long boardId, @Param("email") String email);

    @Query(value = "SELECT * FROM board_is_like_entity where board_id = :boardId and email = :email", nativeQuery = true)
    BoardIsLikeEntity findByBoardIdAndEmail(@Param("boardId") Long boardId, @Param("email") String email);


}
