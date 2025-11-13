package kr.co.wave.security;

import kr.co.wave.jwt.JwtAuthenticationFilter;
import kr.co.wave.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic(HttpBasicConfigurer::disable)        // 기본 HTTP 인증 비활성
                .formLogin(FormLoginConfigurer::disable)    // 폼 로그인 비활성
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        // 인가 설정
        http.authorizeHttpRequests(authorize -> authorize
                /*
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers("/member/**").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
                .requestMatchers("/guest/**").permitAll()
                .requestMatchers("/user/**").hasAnyRole("ADMIN", "MANAGER")
                 */
                .anyRequest().permitAll()
        );

        // 기타 설정
        http.csrf(CsrfConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}