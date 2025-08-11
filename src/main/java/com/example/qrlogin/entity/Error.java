package com.example.qrlogin.entity;

import com.example.qrlogin.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class Error {
    private int status;
    private String code;
    private String msg;

    public static ResponseEntity<Error> toEntity(ErrorCode e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(Error.builder()
                        .status(e.getHttpStatus().value())
                        .code(e.getCode())
                        .msg(e.getMsg())
                        .build());
    }
}
