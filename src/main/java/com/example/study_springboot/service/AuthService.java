package com.example.study_springboot.service;

import com.example.study_springboot.dto.request.SignupRequest;
import com.example.study_springboot.entity.User;
import com.example.study_springboot.repository.UserRepository;
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
                        .userId(signupRequest.getUserId())
                        .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build());
        return "Success!";
    }
}
