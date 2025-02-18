package com.example.waffle_project.RestController;

import com.example.waffle_project.Dto.BoardDto;
import com.example.waffle_project.Dto.BoardIsLikeDto;
import com.example.waffle_project.Dto.CommentIsLikeDto;
import com.example.waffle_project.Service.BoardService;
import com.example.waffle_project.Service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Board-Controller", description = "게시판 관련 API")
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

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
    public ResponseEntity<?> getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @Operation(summary = "게시글 전체 조회", description = "게시판 상관 없이, 등록된 모든 게시글을 조회함")
    @GetMapping("/boards")
    public ResponseEntity<?> getBoard(){
        return boardService.getBoards_ALL();
    }

    @Operation(summary = "게시글 전체 조회(게시판별)", description =
            "(b001 = 금융) "+
            "(b002 = 복지) "+
            "(b003 = 주거) "+
            "(b004 = 자기계발) "+
            "(b005 = 자유) "+
            "(b006 = q&a) ")
    @GetMapping("/boards/{boardType}")
    public ResponseEntity<?> getBoards_TYPE(@PathVariable String boardType) {
        return boardService.getBoards_TYPE(boardType);
    }


    @Operation(
            summary = "게시글 등록",
            description = "게시글을 등록하는 api(multipart/form-data)"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createBoard(
            @Parameter(description = "게시글 데이터")
            //@ModelAttribute(value = "board") BoardDto boardDto,
            @RequestPart(value = "board") BoardDto boardDto,

            @Parameter(description = "이미지 파일 (선택사항)")
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        return boardService.createBoard(boardDto, file);
    }

    @Operation(
            summary = "게시판 하트 api",
            description = "게시판 하트를 누르는 api"
    )
    @PostMapping("/board/like")
    public ResponseEntity<?> IsLikeBoard(@RequestBody BoardIsLikeDto boardIsLikeDto){
        return boardService.IsLikeBoard(boardIsLikeDto);
    }

    @Operation(
            summary = "댓글 하트 api",
            description = "댓글 하트를 누르는 api"
    )
    @PostMapping("/comment/like")
    public ResponseEntity<?> IsLikeComment(@RequestBody CommentIsLikeDto commentIsLikeDto){
        return boardService.IsLikeComment(commentIsLikeDto);
    }





}
