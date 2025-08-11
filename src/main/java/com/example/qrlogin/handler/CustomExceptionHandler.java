package com.example.qrlogin.handler;
import com.example.qrlogin.entity.Error;
import com.example.qrlogin.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Error> handlerCustomException(CustomException e) {
        return Error.toEntity(e.getErrorCode());
    }
}
