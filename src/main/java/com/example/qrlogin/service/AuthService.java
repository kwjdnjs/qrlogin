package com.example.qrlogin.service;

import com.example.qrlogin.dto.SignUpRequestDto;
import com.example.qrlogin.dto.SignUpResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        return new SignUpResponseDto(requestDto.getUsername());
    }
}
