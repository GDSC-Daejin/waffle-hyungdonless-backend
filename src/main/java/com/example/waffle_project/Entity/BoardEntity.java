package com.example.waffle_project.Entity;

import com.example.waffle_project.Dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 255)
    private String boardType; //게시판 타입

    @Column(length = 255)
    private String category; //게시판 내 카테고리

    @Column(length = 255)
    private String writer; //작성자 닉네임

    @Column
    private Long view; //조회수

    @Column
    private Long commentCount; //댓글수

    @Column
    private Long likeCount; //하트

    @Column(length = 255)
    private String createDate; //작성일

    @Column(length = 255)
    private String imageURL; //게시판 이미지 url

    public BoardDto toDto(){
        BoardDto boardDto = new BoardDto();
        boardDto.setId(this.id);
        boardDto.setTitle(this.title);
        boardDto.setContent(this.content);
        boardDto.setBoardType(this.boardType);
        boardDto.setCategory(this.category);
        boardDto.setWriter(this.writer);
        boardDto.setView(this.view);
        boardDto.setCommentCount(this.commentCount);
        boardDto.setLikeCount(this.likeCount);
        boardDto.setCreateDate(this.createDate);
        boardDto.setImageURL(this.imageURL);
        return boardDto;
    }

}
