package com.example.study_springboot.domain.user.presentaion;

import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.domain.user.presentaion.dto.request.SignInRequest;
import com.example.study_springboot.domain.user.presentaion.dto.request.SignupRequest;
import com.example.study_springboot.domain.user.service.UserSignInService;
import com.example.study_springboot.domain.user.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserSignInService userSignInService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest signupRequest) {
        userSignUpService.signup(signupRequest);
    }

    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return userSignInService.signIn(signInRequest);
    }

}
