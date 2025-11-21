package kr.co.wave.controller.company;

import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.service.cs.question.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final NoticeService noticeService;

    @GetMapping({"/company", "/company/", "/company/index"})
    public String companyIndex(Model model) {

        List<NoticeDTO> noticeList = noticeService.getNoticeAll();

        model.addAttribute("noticeList", noticeList);

        return "company/index";
    }


}
