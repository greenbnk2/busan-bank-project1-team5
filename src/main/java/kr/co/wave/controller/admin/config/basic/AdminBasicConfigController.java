package kr.co.wave.controller.admin.config.basic;

import jakarta.persistence.EntityNotFoundException;
import kr.co.wave.dto.config.SiteInfoDTO;
import kr.co.wave.entity.config.SiteInfo;
import kr.co.wave.entity.config.Terms;
import kr.co.wave.handler.ImageHandler;
import kr.co.wave.service.config.BasicConfigService;
import kr.co.wave.service.config.SiteInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminBasicConfigController {

    private final BasicConfigService basicConfigService;
    private final SiteInfoService siteInfoService;
    private final ImageHandler imageHandler;

    // 이미지 업로드 처리
    @PostMapping("/admin/config/basic/upload")
    public String uploadImages(
            @RequestParam(value = "header_logo", required = false) MultipartFile headerLogo,
            @RequestParam(value = "footer_logo", required = false) MultipartFile footerLogo,
            @RequestParam(value = "favicon", required = false) MultipartFile favicon,
            @RequestParam(value = "siteId") String siteId ){

        SiteInfoDTO siteInfoDTO = siteInfoService.getSiteInfoById(Integer.parseInt(siteId));

        try {

            // config 폴더에 저장
            if (headerLogo != null && !headerLogo.isEmpty()) {
                String savedPath = imageHandler.saveFiles(List.of(headerLogo), "config").get("header_logo");
                siteInfoDTO.setHeaderLogo(savedPath);
            }

            if (footerLogo != null && !footerLogo.isEmpty()) {
                String savedPath = imageHandler.saveFiles(List.of(footerLogo), "config").get("footer_logo");
                siteInfoDTO.setFooterLogo(savedPath);
            }

            if (favicon != null && !favicon.isEmpty()) {
                String savedPath = imageHandler.saveFiles(List.of(favicon), "config").get("favicon");
                siteInfoDTO.setFavicon(savedPath);
            }

            basicConfigService.updateConfig(siteInfoDTO);

        } catch (Exception e) {
            System.out.println("이미지 업로드 중 오류가 발생했습니다: " + e.getMessage());
        }

        return "redirect:/admin/config/basic";
    }
}
