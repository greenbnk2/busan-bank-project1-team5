package kr.co.wave.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final String AUTH_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.startsWith("/draft/member/api/login") || uri.startsWith("/draft/member/api/signup")) {
            // 로그인, 회원가입 요청은 JWT 체크하지 않음
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 추출
        String header = request.getHeader(AUTH_HEADER);
        log.info("header : {}", header);

        if(header == null){
            log.info("header is null");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(TOKEN_PREFIX.length());
        log.info("token : {}", token);

        // 토큰 검사
        try {
            jwtProvider.validateToken(token);
            log.info("token validated");

            // 시큐리티 인증처리
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.info("token validation failed");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}