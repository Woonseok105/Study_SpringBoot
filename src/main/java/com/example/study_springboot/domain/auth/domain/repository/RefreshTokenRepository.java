package com.example.study_springboot.domain.auth.domain.repository;

import com.example.study_springboot.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String refreshToken);

}
