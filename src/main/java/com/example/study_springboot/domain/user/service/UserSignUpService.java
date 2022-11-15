package com.example.study_springboot.domain.user.service;

import com.example.study_springboot.domain.user.exception.UserExistException;
import com.example.study_springboot.domain.user.presentaion.dto.request.SignupRequest;
import com.example.study_springboot.domain.user.domain.User;
import com.example.study_springboot.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        if (userRepository.findByAccountId(signupRequest.getAccountId()).isPresent()) {
            throw UserExistException.EXCEPTION;
        }

        userRepository.save(User.builder()
                        .accountId(signupRequest.getAccountId())
                        .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build());
    }
}
