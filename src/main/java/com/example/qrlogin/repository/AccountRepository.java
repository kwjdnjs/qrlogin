package com.example.qrlogin.repository;

import com.example.qrlogin.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Account findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
