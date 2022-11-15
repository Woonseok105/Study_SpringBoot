package com.example.study_springboot.domain.auth.service;

import com.example.study_springboot.domain.auth.domain.RefreshToken;
import com.example.study_springboot.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.domain.user.domain.User;
import com.example.study_springboot.domain.user.domain.repository.UserRepository;
import com.example.study_springboot.domain.user.exception.UserExistException;
import com.example.study_springboot.global.prorperties.AuthProperty;
import com.example.study_springboot.global.prorperties.JwtProperty;
import com.example.study_springboot.global.security.jwt.JwtTokenProvider;
import com.example.study_springboot.infrastructure.feign.client.GoogleAuth;
import com.example.study_springboot.infrastructure.feign.client.GoogleInfo;
import com.example.study_springboot.infrastructure.feign.dto.request.GoogleCodeRequest;
import com.example.study_springboot.infrastructure.feign.dto.response.GoogleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class GoogleAuthService {

    private final GoogleAuth googleAuth;
    private final GoogleInfo googleInfo;
    private final AuthProperty authProperty;
    private final JwtProperty jwtProperty;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(String code) {
        String accessToken = googleAuth.googleAuth(
                GoogleCodeRequest.builder()
                        .code(URLDecoder.decode(code, StandardCharsets.UTF_8))
                        .clientId(authProperty.getClientId())
                        .clientSecret(authProperty.getClientSecret())
                        .redirectUri(authProperty.getRedirectUrl())
                        .build()
        ).getAccessToken();

        GoogleInfoResponse response = googleInfo.googleInfo(accessToken);

        String email = response.getEmail();
        String name = response.getName();

        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(email)
                .token(refreshToken)
                .ttl(jwtProperty.getRefreshExp())
                .build());


        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void createUser(String email, String name) {
        if (userRepository.findByAccountId(email).isPresent()) {
            throw UserExistException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .accountId(email)
                .build());
    }

}
