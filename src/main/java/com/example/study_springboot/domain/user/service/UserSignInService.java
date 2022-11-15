package com.example.study_springboot.domain.user.service;

import com.example.study_springboot.domain.auth.exception.PasswordMisMatchException;
import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.domain.user.domain.User;
import com.example.study_springboot.domain.user.domain.repository.UserRepository;
import com.example.study_springboot.domain.user.exception.UserNotFoundException;
import com.example.study_springboot.domain.user.presentaion.dto.request.SignInRequest;
import com.example.study_springboot.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByAccountId(signInRequest.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        } //matches : 암호화 값을 비교

        String accessToken = jwtTokenProvider.generateAccessToken(signInRequest.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(signInRequest.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
