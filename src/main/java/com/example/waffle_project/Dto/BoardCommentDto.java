package com.example.waffle_project.Dto;

import com.example.waffle_project.Entity.BoardCommentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCommentDto {
    private Long id;
    private String writer; //작성자 이메일
    private String nickname; //작성자 닉네임
    private Long boardId; //게시글 번호
    private String content; //댓글 내용
    private Long likeCount; //하트
    private String createDate; //작성일

    public BoardCommentEntity toEntity(){
        BoardCommentEntity boardCommentEntity = new BoardCommentEntity();
        boardCommentEntity.setWriter(this.writer);
        boardCommentEntity.setNickname(this.nickname);
        boardCommentEntity.setBoardId(this.boardId);
        boardCommentEntity.setContent(this.content);
        boardCommentEntity.setLikeCount(this.likeCount);
        boardCommentEntity.setCreateDate(this.createDate);
        return boardCommentEntity;
    }
}
