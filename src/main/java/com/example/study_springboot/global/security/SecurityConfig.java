package com.example.study_springboot.global.security;

import com.example.study_springboot.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //csrf 에 대한 설정이 기본으로 Enabled 상태로 되어있다.csrf 에 대한 토큰을 받도록 명시되어있고 토큰이 서버 헤더로 부터 전송되지 않을경우 403 에러를 리턴한다.

                .formLogin().disable()
                .sessionManagement()
                //스프링 시큐리티 세션정책
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음 ->JWT 같은토큰방식을 쓸때 사용하는 설정

                .and().cors();
        http
                .authorizeRequests() //특정 리소스의 접근 허용 또는 특정 권한을 가진 사용자만 접근을 가능하게 할 수 있다
                .anyRequest().permitAll() //모든 리소스, 인증절차 없이 접근 허용
                .and().apply(new FilterConfig(objectMapper, jwtTokenProvider));

        return  http.build();
    }

}
