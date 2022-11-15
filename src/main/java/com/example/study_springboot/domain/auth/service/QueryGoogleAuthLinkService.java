package com.example.study_springboot.domain.auth.service;

import com.example.study_springboot.global.prorperties.AuthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryGoogleAuthLinkService {

    private static final String URL = "%s?client_id=%s&redirect_uri=%s&response_type=code"
            + "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    private final AuthProperty authProperty;

    public String execute() {
        return String.format(URL,
                authProperty.getBaseUrl(),
                authProperty.getClientId(),
                authProperty.getRedirectUrl());
    }
}