package kr.co.wave.interceptor;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wave.config.AppInfo;
import kr.co.wave.dto.config.BasicConfigDTO;
import kr.co.wave.service.config.BasicConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
public class AppInfoInterceptor implements HandlerInterceptor {
    /*
        Interceptor
         - 클라이언트 요청을 컨트롤러 사이에서 특정 작업을 수행하는 컴포넌트
         - HTTP 요청을 가로채고, 요청이 컨트롤러에 도달 전 또는 후에 추가 작업 수행
     */

    private AppInfo appInfo;
    private final BasicConfigService basicConfigService;

    @PostConstruct
    public void init() {
        reloadAppInfo();
    }

    public synchronized void reloadAppInfo() {
        BasicConfigDTO basicConfigDTO = basicConfigService.getBasicConfig();
        appInfo = new AppInfo();
        appInfo.setAppName(basicConfigDTO.getSiteInfo().getTitle());
        appInfo.setAppSubName(basicConfigDTO.getSiteInfo().getSubtitle());
        appInfo.setAppVersion(basicConfigDTO.getSiteInfo().getVersion());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러 수행 전
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 컨트롤러 수행 후
        if(modelAndView != null) {
            modelAndView.addObject("appInfo", appInfo);
        }
    }
}