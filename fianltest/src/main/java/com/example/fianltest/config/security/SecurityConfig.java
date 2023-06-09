package com.example.fianltest.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;


    public SecurityConfig(JwtTokenProvider jwtTokenProvider) { // 상속자
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ctrl+o 로 오버라이드 메서드 불러옴
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/sign-api/sign-in","/sign-api/sign-up" ,"/sign-api/exception"
                        ,"/product/listOrderByPrice","/product/byName","/product/","/product/list",
                        "/board/list","/board/listOrderByCreateAt","/board/byUserId")
                .permitAll()
                .antMatchers("/product","/order/list","/order/listByUserId","/order/listByProductId","/order/").hasRole("ADMIN")
                .antMatchers("/product","/order","/order/list","/order/listByUserId","/order/listByProductId","/order/").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/user/**").permitAll() // user/ 경로에 대한 권한 누구나 접근 가능
                .anyRequest().hasAnyRole("USER","ADMIN") // 이외 요청은 USER, ADMIN 권한을 가진 사용자만 허용
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenicationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // 로그인할 때 실행되는 UsernamePasswordAuthenticationFilter 앞에 추가하여 인증 프로세스에서 JWT 인증을 수행하도록 설정, 즉 JWT 토큰을 사용하여 사용자의 인증 상태를 확인하고, 인증된 사용자로써의 권한을 부여하는 역할
    }

    // ctrl+o 로 오버라이드 메서드 불러옴
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/v3/api-docs/**"
                , "/swagger-ui/**"
                , "/swagger-ui/index.html"
                , "/swagger/**"); // swagger 안되면 안되니까 예외처리
    }

}
