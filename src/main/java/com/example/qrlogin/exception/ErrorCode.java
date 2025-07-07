package com.example.qrlogin.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "RE-001", "refresh token이 쿠키에 없습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String msg;
}
