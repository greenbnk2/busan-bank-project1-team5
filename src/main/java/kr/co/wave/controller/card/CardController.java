package kr.co.wave.controller.card;

import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.dto.card.CardWithInfoDTO;
import kr.co.wave.service.board.company.NoticeService;
import kr.co.wave.service.card.CardService;
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
    private final CardService cardService;

    @GetMapping("/card/list")
    public String cardList(@RequestParam(defaultValue = "") String searchType,
                           @RequestParam(defaultValue = "") String keyword,
                           @RequestParam(defaultValue = "0") int page,
                           Model model) {

        // 서비스에서 카드 전체 , 혜택, 연회비 포함된 DTO가져오기 + card status = '활성' 만 가져오기
        Page<CardWithInfoDTO> cardPage = cardService.getCardWithInfoAllBySearch2(searchType, keyword,page,12);

        model.addAttribute("cardPage", cardPage);
        model.addAttribute("cards",cardPage.getContent()); // 리스트만 가져오기


        return "card/list";
    }


    @GetMapping("/card/view")
    public String cardView(int cardId, Model model) {
        CardWithInfoDTO card = cardService.getCardWithInfoById(cardId);
        model.addAttribute("fff", card);

        return "card/view";
    }

    @GetMapping("/card/view2")
    public String viewCard(int cardId, Model model) {
        CardWithInfoDTO cardInfo = cardService.getCardWithInfoById(cardId);
        model.addAttribute("cardItem", cardInfo);

        return "card/view2";
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


}
