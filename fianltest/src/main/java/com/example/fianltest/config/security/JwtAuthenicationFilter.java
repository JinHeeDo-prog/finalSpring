package com.example.fianltest.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenicationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenicationFilter(JwtTokenProvider jwtTokenProvider) { // 생성자
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request); // jwtTokenProvider객체를 통해 토큰을 추출
        System.out.println("[doFilterInternal] token :"+ token);
        if(token != null && jwtTokenProvider.validateToken(token)) { // 토큰이 있으면서 유효한 토큰이라면 if문
            Authentication authentication = jwtTokenProvider.getAuthenication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // authentication에 토큰을 세팅
        }
        filterChain.doFilter(request, response); // if문 조건이 아닐때 실행
    }
}
