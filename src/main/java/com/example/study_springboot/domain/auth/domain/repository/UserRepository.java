package com.example.study_springboot.domain.auth.domain.repository;

import com.example.study_springboot.domain.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);
}
