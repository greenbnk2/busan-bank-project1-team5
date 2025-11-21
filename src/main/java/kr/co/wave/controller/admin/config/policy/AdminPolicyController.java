package kr.co.wave.controller.admin.config.policy;

import kr.co.wave.dto.config.TermsDTO;
import kr.co.wave.dto.config.TermsRepositoryDTO;
import kr.co.wave.service.config.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

@Controller
@RequiredArgsConstructor
public class AdminPolicyController {


    private final TermsService termsService;

    // 약관관리
    @GetMapping("/admin/config/policy/list")
    public String adminConfigPolicy(@RequestParam(required = false) String searchType,
                                    @RequestParam(required = false) String keyword,
                                    @RequestParam(defaultValue = "0") int page,
                                    Model model) {

        Page<TermsRepositoryDTO> termsList = termsService.getTermsAllBySearch(searchType, keyword, page, 10);
        model.addAttribute("termsList", termsList);

        return "admin/config/policy/list";
    }

    // 약관 수정
    @PostMapping("/admin/config/policy/update")
    public String adminConfigUpdate(TermsDTO termsDTO) {

        System.out.println(termsDTO);
        termsService.updateTerms(termsDTO);
        return "redirect:/admin/config/policy/list";
    }

    // 약관 등록
    @GetMapping("/admin/config/policy/register")
    public String adminConfigRegister() {
        return "admin/config/policy/register";
    }

    // 약관 등록
    @PostMapping("/admin/config/policy/register")
    public String adminConfigRegister(TermsDTO termsDTO) {

        // DB에 저장
        termsService.registerTerms(termsDTO);

        return "redirect:/admin/config/policy/register";
    }

    @GetMapping("/terms/download/{id}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable int id) {
        return termsService.fileDownload(id);
    }


}
