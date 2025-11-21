package kr.co.wave.controller.admin.admin;

import kr.co.wave.dto.admin.AdminDTO;
import kr.co.wave.repository.admin.AdminRepository;
import kr.co.wave.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminManageController {

    private final AdminService adminService;

    // 관리자 로그인 페이지 이동
    @GetMapping("/admin/admin/list")
    public String login(@RequestParam(required = false) String searchType,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(defaultValue = "0") int page,
                        Model model){

        Page<AdminDTO> adminList = adminService.getAdminAllBySearch(searchType, keyword, page, 10);

        model.addAttribute("adminList", adminList);

        return "admin/admin/list";
    }

}