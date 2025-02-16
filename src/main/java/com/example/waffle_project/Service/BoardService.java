package com.example.waffle_project.Service;

import com.example.waffle_project.Dto.BoardDto;
import com.example.waffle_project.Repository.*;
import com.example.waffle_project.Utility.SmsUtil;
import com.example.waffle_project.Utility.Utility;
import jakarta.servlet.http.HttpSession;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardTypeRepository boardTypeRepository;

    @Autowired
    private BoardCommentRepository boardCommentRepository;

    @Autowired
    private ChatBotMessageRepository chatBotMessageRepository;

    @Autowired
    private Utility utility;

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private HttpSession session; //세션 객체

    @Autowired
    private BCryptPasswordEncoder hash; //비밀번호 저장을 위한 해쉬화 객체

    @Autowired
    private ResourceLoader resourceLoader; //파일 저장을 위한 리소스 로더

    public ResponseEntity<?> createBoard(BoardDto boardDto, MultipartFile file) {
        Map<String, String> response = new HashMap<>(); //json 응답을 위한 맵
        String filePath;
        System.out.println("createBoard 메서드 실행");
        if (file != null && !file.isEmpty()) //file이 존재할 경우 처리
        {
            try { //이미지파일 업로드 예외처리
                System.out.println("이미지 처리 로직 실행");
                String fileName = file.getOriginalFilename(); //파일 이름
                Resource resource = resourceLoader.getResource("classpath:static/images");
                String uploadDir = resource.getFile().getAbsolutePath();
                filePath = "/images/" + fileName;
                file.transferTo(new File(uploadDir + File.separator + fileName));

                boardDto.setImageURL(filePath); //이미지 파일 경로 저장

            } catch (Exception e) {
                response.put("error", e.getMessage());
                response.put("message", "이미지 저장에 실패하였습니다.");
                response.put("status", HttpStatus.BAD_REQUEST.toString());

                return ResponseEntity.badRequest().body(response);
            }
        } else{
            filePath = null; //이미지 파일이 없을 경우 null로 초기화
            boardDto.setImageURL(filePath); //이미지 파일 경로 저장
        }

        boardDto.setCommentCount(0l); //게시물 등록 시 댓글 수 0으로 초기화
        boardDto.setCreateDate(utility.getToday().toString());
        boardDto.setLikeCount(0l); //게시물 등록 시 좋아요 수 0으로 초기화
        boardDto.setView(0l); //게시물 등록 시 조회수 0으로 초기화

        boardRepository.save(boardDto.toEntity()); //게시물 저장

        response.put("message", "게시물 등록에 성공하였습니다.");
        response.put("status", HttpStatus.OK.toString());
        return ResponseEntity.ok(response);
    }



}
