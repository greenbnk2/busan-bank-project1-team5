package kr.co.wave.controller.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {

    @GetMapping("/faq/list")
    public String faqList(){
        return "faq/list";
    }
}
