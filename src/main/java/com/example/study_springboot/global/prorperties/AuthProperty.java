package com.example.study_springboot.global.prorperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
//@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("auth.google")
public class AuthProperty {

    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;

    public AuthProperty(String baseUrl, String clientId, String clientSecret, String redirectUrl) {
        this.baseUrl = baseUrl;
        this.clientId=clientId;
        this.clientSecret=clientSecret;
        this.redirectUrl=redirectUrl;

    }

}
