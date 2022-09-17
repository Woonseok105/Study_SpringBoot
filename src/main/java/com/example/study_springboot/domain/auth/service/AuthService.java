package com.example.study_springboot.domain.auth.service;

import com.example.study_springboot.domain.auth.presentaion.dto.request.SignupRequest;
import com.example.study_springboot.domain.auth.domain.User;
import com.example.study_springboot.domain.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String signup(SignupRequest signupRequest) {
        userRepository.save(User.builder()
                        .accountId(signupRequest.getAccountId())
                        .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build());
        return "Success!";
    }
}
