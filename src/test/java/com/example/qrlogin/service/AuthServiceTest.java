package com.example.qrlogin.service;

import com.example.qrlogin.dto.SignUpRequestDto;
import com.example.qrlogin.dto.SignUpResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    void signUp() {
        String email = "email@test";
        String username = "testname";
        String password = "testpassword";

        SignUpRequestDto request = new SignUpRequestDto(email, username, password);
        SignUpResponseDto response = authService.signUp(request);

        assertEquals(response.getUsername(), request.getUsername());
    }
}