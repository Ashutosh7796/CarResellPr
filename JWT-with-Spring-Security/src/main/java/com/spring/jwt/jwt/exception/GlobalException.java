package com.spring.jwt.jwt.exception;


import com.spring.jwt.exception.BaseException;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(com.spring.jwt.exception.BaseException.class)
    public ResponseEntity<BaseResponseDTO> handleBaseException(BaseException e){
        BaseResponseDTO response = BaseResponseDTO.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.ok(response);
    }
}
