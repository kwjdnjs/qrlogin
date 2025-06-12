package com.example.qrlogin.service;

import com.example.qrlogin.dto.LoginRequestDto;
import com.example.qrlogin.dto.LoginResponseDto;
import com.example.qrlogin.dto.SignUpRequestDto;
import com.example.qrlogin.dto.SignUpResponseDto;
import com.example.qrlogin.entity.Account;
import com.example.qrlogin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        if (accountRepository.existsByUsername(requestDto.getUsername())){
            throw new RuntimeException();
        }
        if (accountRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException();
        }

        Account account = accountRepository.save(requestDto.toEntity());

        return new SignUpResponseDto(account.getUsername());
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        if (!accountRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException();
        }

        Account account = accountRepository.findByEmail(requestDto.getEmail()).orElseThrow();

        return new LoginResponseDto(account.getUsername());
    }
}
