package com.example.qrlogin.controller;

import com.example.qrlogin.dto.LoginRequestDto;
import com.example.qrlogin.dto.LoginResponseDto;
import com.example.qrlogin.dto.SignUpRequestDto;
import com.example.qrlogin.dto.SignUpResponseDto;
import com.example.qrlogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = authService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
