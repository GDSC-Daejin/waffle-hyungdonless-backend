package com.example.waffle_project.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board-Controller", description = "게시판 관련 API")
@RestController
@RequestMapping("/board")
public class BoardController {

    @Operation(summary = "특정 게시글 삭제", description = "게시글 id를 통해 특정 게시글을 삭제함")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id){
        return null;
    }

    @Operation(summary = "특정 게시글 업데이트", description = "게시글 id를 통해 특정 게시글의 내용을 업데이트함")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id){
        return null;
    }

    @Operation(summary = "특정 게시글 조회", description = "게시글 id를 통해 특정 게시글을 조회함")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(Long id){
        return null;
    }

    @Operation(summary = "게시글 전체 조회", description = "게시판 상관 없이, 등록된 모든 게시글을 조회함")
    @GetMapping
    public ResponseEntity<?> getBoard(){
        return null;
    }

    @Operation(summary = "게시글 전체 조회(게시판별)", description = "게시판별로 등록된 모든 게시글을 조회함")
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardId(@PathVariable Long boardId){
        return null;
    }

    @Operation(summary = "게시글 등록, description = ", description = "게시글을 등록하는 api")
    @PostMapping
    public ResponseEntity<?> createBoard(){
        return null;
    }


}
