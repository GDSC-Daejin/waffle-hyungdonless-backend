package com.example.waffle_project.Entity;

import com.example.waffle_project.Dto.BoardCommentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardCommentEntity {
    @Id
    @Column(length = 255)
    private String writer; //작성자 이메일, pk

    @Column(length = 255)
    private String nickname; //작성자 닉네임

    @Column(length = 255)
    private String boardType; //게시판 타입

    @Column(length = 255)
    private String content; //댓글 내용

    @Column
    private Long likeCount; //하트

    @Column(length = 255)
    private String createDate; //작성일

    public BoardCommentDto toDto(){
        BoardCommentDto boardCommentDto = new BoardCommentDto();
        boardCommentDto.setWriter(this.writer);
        boardCommentDto.setNickname(this.nickname);
        boardCommentDto.setBoardType(this.boardType);
        boardCommentDto.setContent(this.content);
        boardCommentDto.setLikeCount(this.likeCount);
        boardCommentDto.setCreateDate(this.createDate);
        return boardCommentDto;
    }
}
