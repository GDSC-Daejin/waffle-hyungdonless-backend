package com.example.waffle_project.Dto;

import com.example.waffle_project.Entity.BoardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import kotlinx.serialization.descriptors.PrimitiveKind;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    @Schema(hidden = true)
    private Long id;

    @Schema(example = "게시글 제목")
    private String title;

    @Schema(example = "게시글 내용")
    private String content;

    @Schema(example = "b001")
    private String boardType; //게시판 타입

    @Schema(example = "월급 관리 및 예산")
    private String category; //게시판 내 카테고리

    @Schema(example = "닉네임")
    private String nickname; //작성자 닉네임

    @Schema(example = "test123@gmail.com")
    private String email; //작성자 이메일

    @Schema(hidden = true)
    private Long view; //조회수

    @Schema(hidden = true)
    private Long commentCount; //댓글수

    @Schema(hidden = true)
    private Long likeCount; //하트

    @Schema(hidden = true)
    private String createDate; //작성일

    @Schema(hidden = true)
    private String imageURL; //게시판 이미지 url

    @Schema(hidden = true) //현재 유저가 해당 게시글에 좋아요를 눌렀는지 여부
    private String isLike;

    public BoardEntity toEntity(){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(this.id);
        boardEntity.setTitle(this.title);
        boardEntity.setContent(this.content);
        boardEntity.setBoardType(this.boardType);
        boardEntity.setCategory(this.category);
        boardEntity.setNickname(this.nickname);
        boardEntity.setEmail(this.email);
        boardEntity.setView(this.view);
        boardEntity.setCommentCount(this.commentCount);
        boardEntity.setLikeCount(this.likeCount);
        boardEntity.setCreateDate(this.createDate);
        boardEntity.setImageURL(this.imageURL);
        return boardEntity;
    }
}

