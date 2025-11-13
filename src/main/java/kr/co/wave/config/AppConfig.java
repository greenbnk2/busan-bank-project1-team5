package kr.co.wave.config;

import kr.co.wave.interceptor.AppInfoInterceptor;
import kr.co.wave.service.config.BasicConfigService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**") // URL로 접근할 때의 경로
                .addResourceLocations("file:///C:/Users/GGG/Desktop/workspace/spring/wave/uploads/");
    }

    @Bean
    public AppInfoInterceptor appInfoInterceptor(BasicConfigService basicConfigService) {
        return new AppInfoInterceptor(basicConfigService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInfoInterceptor(null));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS 설정 : API 요청을 하는 외부 요청 Origin 설정
        registry
                .addMapping("/**")                                  // 서버의 모든 엔드포인트에 대해 허용
                .allowedOriginPatterns("*")   // 서버의 요청을 허용할 Origin
                .allowedHeaders("*")                                // 모든 헤더 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 메서드
                .allowCredentials(true)                                     // 자격증명 허용
                .maxAge(3600);                                              // 요청 유효시간
    }
}
