package kr.co.wave.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/product/list")
    public String productList(){
        return "product/list";
    }
}
