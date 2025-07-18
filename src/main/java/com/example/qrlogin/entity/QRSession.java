package com.example.qrlogin.entity;

import com.example.qrlogin.enumrate.SessionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="qrSession", timeToLive = 86400)
@Getter
@Setter
@AllArgsConstructor
public class QRSession {
    @Id
    private String sessionId;
    @Enumerated(EnumType.STRING)
    private SessionStatus status;
    private String accessToken;
    private String refreshToken;

    public static QRSession create(String sessionId, SessionStatus status) {
        return new QRSession(sessionId, status, null, null);
    }
}
