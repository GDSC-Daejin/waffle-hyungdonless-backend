package com.example.waffle_project.Entity;

import com.example.waffle_project.Dto.BoardTypeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardTypeEntity {

    @Id
    @Column(length = 255, unique = true)
    private String boardType; //게시판 타입 (금융 = B001, 복지 = B002, 주거 = B003, 자기계발 = B004, 자유 = B005)

    @Column(length = 255)
    private String boardName; //게시판 이름

    @Column
    private Long boardCount; //게시판 게시글 수(금융, 복지, 주거, 자기계발, 자유)

    public BoardTypeDto toDto(){
        BoardTypeDto boardTypeDto = new BoardTypeDto();
        boardTypeDto.setBoardType(this.boardType);
        boardTypeDto.setBoardName(this.boardName);
        boardTypeDto.setBoardCount(this.boardCount);
        return boardTypeDto;
    }
}