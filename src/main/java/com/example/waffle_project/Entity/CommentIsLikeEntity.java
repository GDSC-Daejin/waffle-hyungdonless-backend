package com.example.waffle_project.Entity;

import com.example.waffle_project.Dto.CommentIsLikeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CommentIsLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id; //pk

    @Column
    private Long boardId; //fk : 하트를 누른 댓글 id

    @Column(length = 255)
    private String email; //fk : 하트를 누른 유저 email

    public CommentIsLikeDto toDto(){
        CommentIsLikeDto commentIsLikeDto = new CommentIsLikeDto();
        commentIsLikeDto.setId(this.id);
        commentIsLikeDto.setBoardId(this.boardId);
        commentIsLikeDto.setEmail(this.email);
        return commentIsLikeDto;
    }
}
