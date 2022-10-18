package com.example.study_springboot.domain.auth.presentation;

import com.example.study_springboot.domain.auth.presentation.dto.request.UserSignInRequest;
import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.domain.auth.service.ReissueService;
import com.example.study_springboot.domain.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final TokenService tokenService;
    private final ReissueService reissueService;

    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid UserSignInRequest request) {
        return tokenService.userAuth(request);
    }

    @PatchMapping("/token")
    public TokenResponse userReissue(@RequestHeader("Refresh-Token") String refreshToken) {
        return reissueService.userReissue(refreshToken);
    }

}
