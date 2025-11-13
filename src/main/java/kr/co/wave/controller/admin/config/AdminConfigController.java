package kr.co.wave.controller.admin.config;

import kr.co.wave.dto.config.BasicConfigDTO;
import kr.co.wave.dto.config.TermsDTO;
import kr.co.wave.service.config.BasicConfigService;
import kr.co.wave.service.config.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminConfigController {

    private final BasicConfigService basicConfigService;
    private final TermsService termsService;

    // 기본설정
    @GetMapping("/admin/config/basic")
    public String adminConfigBasic(Model model) {
        BasicConfigDTO basicConfig = basicConfigService.getBasicConfig();
        model.addAttribute("basicConfig", basicConfig);
        return "admin/config/basic";
    }

    // 배너관리
    @GetMapping("/admin/config/banner")
    public String adminConfigBanner() {
        return "admin/config/banner";
    }

    // 약관관리
    @GetMapping("/admin/config/policy")
    public String adminConfigPolicy(Model model) {

        List<TermsDTO> termsList = termsService.getTermsAll();
        model.addAttribute("termsList", termsList);

        return "admin/config/policy";
    }

    // 카테고리
    @GetMapping("/admin/config/category")
    public String adminConfigCategory() {
        return "admin/config/category";
    }

    // 버전관리
    @GetMapping("/admin/config/version")
    public String adminConfigVersion() {
        return "admin/config/version";
    }

    // 약관 수정
    @PostMapping("/admin/config/policy/update")
    public String adminConfigUpdate(TermsDTO termsDTO) {

        System.out.println(termsDTO);
        termsService.updateTerms(termsDTO);
        return "redirect:/admin/config/policy";
    }

}
