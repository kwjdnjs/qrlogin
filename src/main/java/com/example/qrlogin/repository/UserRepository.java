package com.example.qrlogin.repository;

import com.example.qrlogin.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Account, Long> {
}
