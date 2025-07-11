package com.example.qrlogin.service;

import com.example.qrlogin.dto.LoginRequestDto;
import com.example.qrlogin.dto.LoginResponseDto;
import com.example.qrlogin.dto.SignUpRequestDto;
import com.example.qrlogin.dto.SignUpResponseDto;
import com.example.qrlogin.entity.Account;
import com.example.qrlogin.entity.QRSession;
import com.example.qrlogin.enumrate.SessionStatus;
import com.example.qrlogin.qr.QRUtil;
import com.example.qrlogin.repository.AccountRepository;
import com.example.qrlogin.repository.QRSessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final QRSessionRepository qrSessionRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        if (accountRepository.existsByUsername(requestDto.getUsername())){
            throw new RuntimeException();
        }
        if (accountRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException();
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Account account = accountRepository.save(requestDto.toEntity());

        return new SignUpResponseDto(account.getUsername());
    }

    public Map<String, Object> generateSessionQR(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();

        qrSessionRepository.save(QRSession.create(sessionId, SessionStatus.PENDING));

        var image = QRUtil.generateQRCodeImage("session:" + sessionId, 200, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("qrImage", "data:image/png;base64," + base64Image);
        return response;
    }
}
