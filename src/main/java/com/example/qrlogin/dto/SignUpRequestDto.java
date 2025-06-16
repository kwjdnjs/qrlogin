package com.example.qrlogin.dto;

import com.example.qrlogin.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String username;
    private String password;

    public Account toEntity() {
        return new Account(null, email, username, password, null);
    }
}
