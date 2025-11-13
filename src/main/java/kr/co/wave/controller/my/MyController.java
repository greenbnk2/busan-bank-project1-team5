package kr.co.wave.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping({"/my","/my/"})
    public String myPage() {
        return "my/index";
    }
}
