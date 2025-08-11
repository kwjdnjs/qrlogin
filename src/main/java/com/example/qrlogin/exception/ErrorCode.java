package com.example.qrlogin.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "AU-001", "이미 존재하는 username입니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "AU-002", "이미 존재하는 email입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "RE-001", "refresh token이 쿠키에 없습니다."),
    QR_GENERATION_FAILED(HttpStatus.BAD_REQUEST, "QR-001", "QR코드 이미지 생성에 실패했습니다."),
    SESSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "SE-001", "QR 세션을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String msg;
}
