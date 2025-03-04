package com.example.waffle_project.Board.domain;

import com.example.waffle_project.Board.dto.BoardIsLikeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BoardIsLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id; //pk

    @Column
    private Long boardId; //fk : 하트를 누른 게시글 id

    @Column(length = 255)
    private String email; //fk : 하트를 누른 유저 email

    public BoardIsLikeDto toDto(){
        BoardIsLikeDto boardIsLikeDto = new BoardIsLikeDto();
        boardIsLikeDto.setId(this.id);
        boardIsLikeDto.setBoardId(this.boardId);
        boardIsLikeDto.setEmail(this.email);
        return boardIsLikeDto;
    }
}
