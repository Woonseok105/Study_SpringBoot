package com.example.study_springboot.domain.auth.service;

import com.example.study_springboot.domain.auth.domain.RefreshToken;
import com.example.study_springboot.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.study_springboot.domain.auth.exception.InvalidRefreshTokenException;
import com.example.study_springboot.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.global.security.jwt.JwtProperty;
import com.example.study_springboot.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperty jwtProperty;

    @Transactional
    public TokenResponse userReissue(String refreshToken) {
        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if (parseToken == null) {
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(parseToken)
                .orElseThrow(()-> RefreshTokenNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshToken.getAccountId());
        redisRefreshToken.updateToken(newRefreshToken, jwtProperty.getRefreshExp());

        String newAccessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getAccountId());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(parseToken)
                .build();
    }

}
