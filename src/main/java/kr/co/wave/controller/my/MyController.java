package kr.co.wave.controller.my;

import kr.co.wave.entity.card.History;
import kr.co.wave.service.card.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final HistoryService historyService;

    @GetMapping({"/my","/my/"})
    public String myPage() {
        return "my/index(new)";
    }

    @GetMapping("/my/apiTest")
    public String myApiTestPage(Model model) {

        List<History> historyList = historyService.getHistoryAll();

        model.addAttribute("historyList", historyList);

        return "my/apiTest";
    }

    @GetMapping({"/my/BillingStates"})
    public String myBillingState(){
        return "my/billing_statements";
    }
}
