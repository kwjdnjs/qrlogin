package com.example.qrlogin.controller;

import com.example.qrlogin.dto.*;
import com.example.qrlogin.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = authService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/api/qr/generate")
    public Map<String, Object> generateQR(HttpServletRequest request) throws Exception {
        return authService.generateSessionQR(request);
    }

    @PostMapping("/api/qr/confirm")
    public ResponseEntity<?> confirmSession(@RequestBody ConfirmSessionRequestDto requestDto) {
        ConfirmSessionResponseDto responseDto = authService.confirmSession(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/api/qr/status")
    public ResponseEntity<?> SessionStatus(@RequestBody SessionStatusRequestDto requestDto) {
        SessionStatusResponseDto responseDto = authService.sessionStatus(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
