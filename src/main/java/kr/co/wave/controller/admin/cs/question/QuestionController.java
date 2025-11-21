package kr.co.wave.controller.admin.cs.question;

import jakarta.persistence.Column;
import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.service.cs.question.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final NoticeService noticeService;

    @GetMapping("/admin/cs/question/list")
    public String noticeList(@RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String sortBy,
                             @RequestParam(defaultValue = "desc") String direction,
                             Model model) {

        Page<NoticeDTO> noticeList = noticeService.getNoticeAllBySearch(searchType, keyword, page, 10, sortBy, direction);
        System.out.println("noticeList = " + noticeList); // ✅ null인지 확인

        model.addAttribute("noticeList", noticeList);
        return "admin/cs/question/list";
    }
}
