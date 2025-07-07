package com.example.qrlogin.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="refresh", timeToLive = 86400)
@Getter
@Setter
@AllArgsConstructor
public class Refresh {
    @Id
    private Long id;
    private String username;
    private String refresh;


    public static Refresh create(String username, String refresh) {
        return new Refresh(null, username, refresh);
    }
}
