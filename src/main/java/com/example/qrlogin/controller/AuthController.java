package com.example.qrlogin.controller;

import com.example.qrlogin.dto.*;
import com.example.qrlogin.exception.CustomException;
import com.example.qrlogin.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto requestDto) throws CustomException {
        SignUpResponseDto responseDto = authService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/api/qr/generate")
    public SessionQrResponse generateQR(HttpServletRequest request) throws CustomException {
        return authService.generateSessionQR(request);
    }

    @PostMapping("/api/qr/confirm")
    public ResponseEntity<?> confirmSession(@RequestBody ConfirmSessionRequestDto confirmRequestDto, HttpServletRequest request) throws CustomException {
        ConfirmSessionResponseDto responseDto = authService.confirmSession(confirmRequestDto, request);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/api/qr/status")
    public ResponseEntity<?> SessionStatus(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        SessionStatusResponseDto responseDto = authService.sessionStatus(request, response);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
