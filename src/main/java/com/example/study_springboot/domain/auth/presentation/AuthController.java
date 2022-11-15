package com.example.study_springboot.domain.auth.presentation;

import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.domain.auth.service.GoogleAuthService;
import com.example.study_springboot.domain.auth.service.QueryGoogleAuthLinkService;
import com.example.study_springboot.domain.auth.service.ReissueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final ReissueService reissueService;
    private final GoogleAuthService googleAuthService;
    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;

    @GetMapping("/google/auth")
    public String queryGoogleAuthLink() {
        return queryGoogleAuthLinkService.execute();
    }

    @GetMapping("/receive-code")
    public TokenResponse googleAuthLogIn(@RequestParam("code") String code) {
        return googleAuthService.execute(code);
    }

    @PutMapping("/token")
    public TokenResponse userReissue(@RequestHeader("Refresh-Token") String refreshToken) {
        return reissueService.userReissue(refreshToken);
    }

}
