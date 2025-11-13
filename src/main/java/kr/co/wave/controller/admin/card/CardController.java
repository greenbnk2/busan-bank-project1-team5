package kr.co.wave.controller.admin.card;

import kr.co.wave.dto.card.AdminCardListDTO;
import kr.co.wave.dto.card.CardDTO;
import kr.co.wave.dto.card.CardRequestDTO;
import kr.co.wave.entity.config.Category;
import kr.co.wave.service.card.CardService;
import kr.co.wave.service.config.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CategoryService categoryService;

    @GetMapping("/admin/card/list")
    public String cardList(@RequestParam(required = false) String searchType,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(required = false) String sortBy,
                           @RequestParam(defaultValue = "desc") String direction,
                           Model model) {

        Page<AdminCardListDTO> cardList = cardService.getCardWithInfoAllBySearch(searchType, keyword, page, 10);

        model.addAttribute("cardList", cardList);
        return "admin/card/list";
    }

    @GetMapping("/admin/card/view")
    public String cardView(@RequestParam String cardId, Model model) {

        CardDTO card = cardService.getCardById(Integer.parseInt(cardId));

        model.addAttribute("card", card);
        return "admin/card/list";
    }


    @GetMapping("/admin/card/register")
    public String cardRegister(Model model) {
        List<Category> categoryList = categoryService.getCategoryAll();

        model.addAttribute("categoryList", categoryList);

        return "admin/card/register";
    }

    @PostMapping("/admin/card/register")
    public void cardRegister(CardRequestDTO cardRequestDTO) {
        cardService.registerCard(cardRequestDTO);
    }

}
