package com.example.waffle_project.Board.dto;

import com.example.waffle_project.Board.domain.BoardIsLikeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardIsLikeDto {
    @Schema(hidden = true)
    private Long id; //pk

    @Schema(example = "1")
    private Long boardId; //fk : 하트를 누른 게시글 id

    @Schema(example = "test123@gmail.com")
    private String email; //fk : 하트를 누른 유저 email

    public BoardIsLikeEntity toEntity(){
        BoardIsLikeEntity boardIsLikeEntity = new BoardIsLikeEntity();
        boardIsLikeEntity.setId(this.id);
        boardIsLikeEntity.setBoardId(this.boardId);
        boardIsLikeEntity.setEmail(this.email);
        return boardIsLikeEntity;
    }
}
