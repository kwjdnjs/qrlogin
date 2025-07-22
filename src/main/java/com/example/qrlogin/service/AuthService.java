package com.example.qrlogin.service;

import com.example.qrlogin.dto.*;
import com.example.qrlogin.entity.Account;
import com.example.qrlogin.entity.QRSession;
import com.example.qrlogin.enumrate.SessionStatus;
import com.example.qrlogin.jwt.JWTUtil;
import com.example.qrlogin.qr.QRUtil;
import com.example.qrlogin.repository.AccountRepository;
import com.example.qrlogin.repository.QRSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final QRSessionRepository qrSessionRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

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

    public SessionQrResponse generateSessionQR(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();

        qrSessionRepository.save(QRSession.create(sessionId, SessionStatus.PENDING));

        var image = QRUtil.generateQRCodeImage(session.toString(), 200, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

        return new SessionQrResponse("data:image/png;base64," + base64Image);
    }

    public ConfirmSessionResponseDto confirmSession(HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader("access");
        jwtUtil.isExpired(accessToken);

        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        QRSession session = qrSessionRepository.findById(request.getSession().getId()).orElseThrow(() -> new EntityNotFoundException("QR 세션을 찾을 수 없습니다."));
        session.setAccessToken(newAccess);
        session.setRefreshToken(newRefresh);
        session.setStatus(SessionStatus.SUCCESS);

        QRSession result = qrSessionRepository.save(session);

        return new ConfirmSessionResponseDto(result.getSessionId(), result.getStatus());
    }

    public SessionStatusResponseDto sessionStatus(HttpServletRequest request, HttpServletResponse response) {
        QRSession session = qrSessionRepository.findById(request.getSession().getId()).orElseThrow(() -> new EntityNotFoundException("QR 세션을 찾을 수 없습니다."));

        if (session.getStatus() == SessionStatus.SUCCESS) {
            response.setHeader("access", session.getAccessToken());
            response.addCookie(jwtUtil.createCookie("refresh", session.getRefreshToken(), 24*60*60));
        }

        return new SessionStatusResponseDto(session.getStatus());
    }
}
