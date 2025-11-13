package kr.co.wave.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kr.co.wave.entity.member.Member;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Component
public class JwtProvider {

    private String issuer;
    private SecretKey secretKey;

    public JwtProvider(@Value("${jwt.issuer}") String issuer,
                       @Value("${jwt.secret}") String secretKey) {

        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(Member member, int days) {

        // 발급일, 만료일 생성
        Date issuedDate = new Date();
        Date expireDate = new Date(issuedDate.getTime() + Duration.ofDays(days).toMillis());

        // 토큰 생성
        String token = Jwts.builder()
                .issuer(issuer)
                .issuedAt(issuedDate)
                .expiration(expireDate)
                .claim("username", member.getMemId())
                .claim("role", member.getRole())
                .signWith(this.secretKey, Jwts.SIG.HS256)
                .compact();

        return token;
    }

    // 토큰으로부터 클레임 추출
    public Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Authentication getAuthentication(String token) {

        // 토큰으로 부터 사용자 정보 가져오기
        Claims claims = getClaims(token);
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);

        // Member 엔티티 생성
        Member member = Member
                .builder()
                .memId(username)
                .role(role)
                .build();

        // 사용자 권한 목록
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+member.getRole()));

        return new UsernamePasswordAuthenticationToken(member, token, authorities);
    }

    public void validateToken(String token) throws Exception {

        try{
            // 토큰 검사(유효성, 만료일)
            Jwts
                    .parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        }catch (Exception e){
            // 토큰에 문제가 있을 경우 예외 넘기기
            throw new Exception(e);
        }
    }
}