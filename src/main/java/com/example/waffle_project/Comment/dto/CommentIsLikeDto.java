package com.example.waffle_project.Comment.dto;

import com.example.waffle_project.Comment.domain.CommentIsLikeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentIsLikeDto {
    private Long id; //pk
    private Long boardId; //fk : 하트를 누른 댓글 id
    private String email; //fk : 하트를 누른 유저 email
    public CommentIsLikeEntity toEntity(){
        CommentIsLikeEntity commentIsLikeEntity = new CommentIsLikeEntity();
        commentIsLikeEntity.setBoardId(this.boardId);
        commentIsLikeEntity.setEmail(this.email);
        return commentIsLikeEntity;
    }
}
