package com.example.study_springboot.domain.auth.presentaion;

import com.example.study_springboot.domain.auth.presentaion.dto.request.SignupRequest;
import com.example.study_springboot.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }
}
