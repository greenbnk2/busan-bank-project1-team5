package kr.co.wave.controller.card;

import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.service.board.company.NoticeService;
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
public class CardController {

    @GetMapping("/card/list")
    public String noticeList() {

        return "card/list";
    }

    @GetMapping("/card/register1")
    public String register1() {

        return "card/register1";
    }

    @GetMapping("/card/register2")
    public String register2() {

        return "card/register2";
    }

    @GetMapping("/card/register3")
    public String register3() {

        return "card/register3";
    }

    @GetMapping("/card/register4")
    public String register4() {

        return "card/register4";
    }

    @GetMapping("/card/register5")
    public String register5() {

        return "card/register5";
    }

    @GetMapping("/card/view")
    public String view() {

        return "card/view";
    }
}
