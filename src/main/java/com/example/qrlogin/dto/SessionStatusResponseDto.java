package com.example.qrlogin.dto;

import com.example.qrlogin.enumrate.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionStatusResponseDto {
    private SessionStatus status;
}
