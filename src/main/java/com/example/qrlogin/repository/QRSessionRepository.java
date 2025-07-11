package com.example.qrlogin.repository;

import com.example.qrlogin.entity.QRSession;
import org.springframework.data.repository.CrudRepository;

public interface QRSessionRepository extends CrudRepository<QRSession, Long> {
    QRSession findBySessionId(String sessionId);
}
