package com.example.fianltest.config.security;

import com.example.fianltest.service.UserDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component //클래스가 컴포넌트 스캔(Component Scan) 기능에 의해 스캔되어 스프링 컨테이너에 빈으로 등록됨
@RequiredArgsConstructor // 필수 매개변수를 생성자에다 만들어준다
public class JwtTokenProvider {

    private final UserDetailService userDetailService;

    // 60min
    private final long tokenValidMilliSecond = 1000L * 60 * 60;
    private String secretKey = "daelimSpring!@#$daelimSpring!@#$daelimSpring!@#$";

    @PostConstruct //  해당 메서드는 빈이 생성된 후 자동으로 호출됩니다. 이 메서드는 빈의 초기화 작업을 수행함
    protected void init() {
        System.out.println("[init] JwtTokenProvider init Start >>>>");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        System.out.println("secretKey : "+secretKey);
    }

    public String createToken(String userUid, List<String> roles) { // 토큰 생성 하는 함수
        System.out.println("[createToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userUid); //빌드 그래들에서 추가한 jsonwebtoken 사용
        claims.put("roles", roles);
        Date now = new Date();
        String token = Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliSecond))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
        System.out.println("token : "+ token);
        return token;
    }

    public Authentication getAuthenication(String token){ // 권한을 체크하는 함수
        System.out.println("[getAuthenication] 토큰 정보 조회");
        UserDetails userDetails = userDetailService.loadUserByUsername(this.getUsername(token)); // Username 토큰 추출해서 getUsername에서 사용
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        System.out.println("[getUsername]" + info);
        return info;
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");   // 토큰 값의 이름을 정해서 헤더에 넣어준다.
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
