package com.example.waffle_project.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDto<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public static <T> ResponseDto<T> response(HttpStatus status, String message, T data) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setStatus(status);
        responseDto.setMessage(message);
        responseDto.setData(data);
        return responseDto;
    }

}
