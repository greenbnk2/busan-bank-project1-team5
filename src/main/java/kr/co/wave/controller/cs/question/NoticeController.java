package kr.co.wave.controller.cs.question;

import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.service.cs.question.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/cs/question/list")
    public String noticeList(@RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String sortBy,
                             @RequestParam(defaultValue = "desc") String direction,
                             Model model) {

        Page<NoticeDTO> noticeList = noticeService.getNoticeAllBySearch(searchType, keyword, page, 10, sortBy, direction);
        System.out.println("noticeList = " + noticeList); // ✅ null인지 확인

        model.addAttribute("noticeList", noticeList);
        return "cs/question/list";
    }

    @GetMapping("/cs/question/view/{noticeId}")
    public String noticeView(@PathVariable int noticeId, Model model) {
        model.addAttribute("notice", noticeService.getNoticeById(noticeId));
        return "cs/question/view";
    }

    @GetMapping("/cs/question/register")
    public String noticeRegister() {
        return "cs/question/register";
    }

    @PostMapping("/cs/question/register")
    public String noticeRegister(NoticeDTO companyNoticeDTO) {
        noticeService.saveNotice(companyNoticeDTO);
        return "redirect:/cs/question/list";
    }

}
