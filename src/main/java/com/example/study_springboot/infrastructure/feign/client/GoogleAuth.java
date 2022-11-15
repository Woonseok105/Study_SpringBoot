package com.example.study_springboot.infrastructure.feign.client;

import com.example.study_springboot.domain.auth.presentation.dto.response.TokenResponse;
import com.example.study_springboot.infrastructure.feign.dto.request.GoogleCodeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuth {

    @PostMapping
    TokenResponse googleAuth(GoogleCodeRequest googleCodeRequest);
    
}
