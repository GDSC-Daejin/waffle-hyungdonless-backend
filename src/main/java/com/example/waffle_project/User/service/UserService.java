package com.example.waffle_project.User.service;

import com.example.waffle_project.Common.response.ResponseDto;
import com.example.waffle_project.Common.security.JwtTokenProvider;
import com.example.waffle_project.Common.util.Utility;
import com.example.waffle_project.User.domain.UserEntity;
import com.example.waffle_project.User.dto.UserDto;
import com.example.waffle_project.User.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder hash;

    @Autowired
    private Utility utility;

    @Autowired
    private HttpSession session;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public ResponseEntity<?> saveUserInfo(UserDto userDto){
        try{
            if(userRepository.findByEmail(userDto.getEmail()) != null){ //회원이 이미 존재할 경우 예외처리
                return ResponseEntity.badRequest()
                        .body(ResponseDto.response(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다.", null));
            }
            userDto.setPassword(hash.encode(userDto.getPassword())); //비밀번호 해쉬화
            UserEntity userEntity = userRepository.save(userDto.toEntity());//회원정보 저장
            return ResponseEntity.ok(ResponseDto.response(HttpStatus.OK, "회원가입 성공", userEntity.toDto()));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }

    }

    public ResponseEntity<?> userFind(String email) {
        UserEntity userEntity = userRepository.findByEmail(email); //email로 회원정보 조회
        if (userEntity == null) { //회원정보가 없을 경우
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, "조회된 데이터가 없습니다.", null));
        } else { //회원정보가 있을 경우
            return ResponseEntity.ok(ResponseDto.response(HttpStatus.OK, "조회 성공", userEntity.toDto()));
        }
    }

    public ResponseEntity<?> userFindAll(){
        List<UserEntity> userEntityList = userRepository.findAll(); //모든 회원정보 조회
        if(userEntityList.isEmpty()){//회원정보가 없을 경우
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, "조회된 데이터가 없습니다.", null));
        } else { //회원정보가 존재할 경우
            List<UserDto> userDtoList = userEntityList.stream().map(UserEntity::toDto).toList();//엔티티 리스트를 dto리스트로 변환
            return ResponseEntity.ok(ResponseDto.response(HttpStatus.OK, "조회 성공", userDtoList));
        }
    }

    public ResponseEntity<?> userLogin(UserDto userDto, HttpServletRequest request){
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        if(userEntity == null){ //해당 이메일로 조회된 회원이 없다면
            return ResponseEntity.badRequest()
                    .body(ResponseDto.response(HttpStatus.BAD_REQUEST, "해당 이메일로 조회된 회원이 없습니다.", null));
        } else {
            if(hash.matches(userDto.getPassword(), userEntity.getPassword())){ //패스워드가 일치한다면
                String token;
                //token 반환 로직
                token = jwtTokenProvider.GetToken(userDto.getEmail());
                //로그인 성공시 세션에 유저의 ip와 email 저장
                session.setAttribute("USER_IP", utility.getClientIpv4(request));
                session.setAttribute("USER_EMAIL", userDto.getEmail());
                log.info("세션에 이메일 등록됨 : {}", session.getAttribute("USER_EMAIL"));

                return ResponseEntity
                        .ok(ResponseDto.response(HttpStatus.OK, "로그인 성공", token));
            } else { //패스워드가 일치하지 않는다면
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ResponseDto.response(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.", null));
            }
        }
    }


    @Transactional
    public ResponseEntity<?> userDelete(String email) {
        UserEntity userEntity = userRepository.findByEmail(email); //email로 회원정보 조회

        if (userEntity == null) {//회원정보가 없을 경우
            return ResponseEntity.badRequest()
                    .body(ResponseDto.response(HttpStatus.BAD_REQUEST, "해당 이메일로 조회된 유저가 없습니다.", null));
        } else { //회원정보가 있을 경우
            try{
                userRepository.deleteByEmail(email); //해당 이메일로 회원정보 삭제
                return ResponseEntity
                        .ok(ResponseDto.response(HttpStatus.OK, "회원정보 삭제 성공", userEntity.toDto()));
            } catch (Exception e) {//삭제에 실패했을 경우
                return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
            }
        }
    }

    @Transactional
    public ResponseEntity<?> userUpdate(UserDto userDto){
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail()); //email로 회원정보 조회
        if(userEntity == null){//회원정보가 없을 경우
            return ResponseEntity.badRequest()
                    .body(ResponseDto.response(HttpStatus.BAD_REQUEST, "해당 이메일로 조회된 회원이 없습니다.", null));
        } else {
            userDto.setPassword(hash.encode(userDto.getPassword())); //비밀번호 해쉬화
            userRepository.save(userDto.toEntity()); //회원정보 업데이트
            return ResponseEntity
                    .ok(ResponseDto.response(HttpStatus.OK, "회원정보 업데이트 성공", userDto));
        }

    }

}
