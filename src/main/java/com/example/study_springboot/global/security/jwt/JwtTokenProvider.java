package com.example.study_springboot.global.security.jwt;

import com.example.study_springboot.domain.auth.domain.RefreshToken;
import com.example.study_springboot.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.study_springboot.global.exception.ExpiredJwtException;
import com.example.study_springboot.global.exception.InvalidJwtException;
import com.example.study_springboot.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperty jwtProperty;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String accountId) {
        return generateToken(accountId, "access", jwtProperty.getAccessExp());
    }

    public String generateRefreshToken(String accountId) {
        String refreshToken = generateToken(accountId, "refresh", jwtProperty.getRefreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .token(refreshToken)
                .ttl(jwtProperty.getRefreshExp())
                .build());

        return refreshToken;
    }

    private String generateToken(String accountId, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getSecretKey())
                .setSubject(accountId) //토큰 용도
                .claim("type", type)
                .setIssuedAt(new Date()) //토큰 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000)) //토큰 만료시간 설정
                .compact(); //토큰 생성
    }
    //claim은 payload 부분에 들어갈 정보 조각들이다.
    //Subject(제목)과 Issued At(발행일), Expiration Time(만료일)

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperty.getHeader());
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperty.getPrefix())) {
            return bearerToken.replace(jwtProperty.getPrefix(), "");
        }
        return null;
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

}
