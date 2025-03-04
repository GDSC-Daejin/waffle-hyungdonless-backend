package com.example.waffle_project.Board.dto;

import com.example.waffle_project.Board.domain.BoardTypeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardTypeDto {
    private String boardType; //게시판 타입 (금융 = B001, 복지 = B002, 주거 = B003, 자기계발 = B004, 자유 = B005)
    private String boardName; //게시판 이름
    private Long boardCount; //게시판 게시글 수(금융, 복지, 주거, 자기계발, 자유)

    public BoardTypeEntity toEntity(){
        BoardTypeEntity boardTypeEntity = new BoardTypeEntity();
        boardTypeEntity.setBoardType(this.boardType);
        boardTypeEntity.setBoardName(this.boardName);
        boardTypeEntity.setBoardCount(this.boardCount);
        return boardTypeEntity;
    }

}
