package kr.co.wave.service.card;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.card.*;
import kr.co.wave.entity.card.AnnualFee;
import kr.co.wave.entity.card.Benefit;
import kr.co.wave.entity.card.Card;
import kr.co.wave.repository.card.AnnualFeeRepository;
import kr.co.wave.repository.card.BenefitRepository;
import kr.co.wave.repository.card.CardRepository;
import kr.co.wave.repository.config.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final AnnualFeeRepository annualFeeRepository;
    private final BenefitRepository benefitRepository;
    private final ModelMapper modelMapper; // Entity와 DTO를 변환해주는 객체

    public Page<CardDTO> getCardAllBySearch(String searchType, String keyword, int page, int size){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findCardAllBySearch(st, kw, pageable);
    }

    public CardDTO getCardById(int cardId){
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()){
            return modelMapper.map(card.get(), CardDTO.class);
        }
        return null;
    }

    /*
    // 카드 정보 가져오기
    public Page<CardDTO> getCardAllBySearch2(String searchType, String keyword, int page, int size){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);

        List<AdminCardListDTO> adminCardListDTOS = new ArrayList<>();

        // 카드 정보
        List<Card> cardList = cardRepository.findAll();

        for(Card card : cardList){
            AdminCardListDTO adminCardListDTO = new AdminCardListDTO();
            adminCardListDTO.setCard(card);

            // 혜택 정보
            List<Benefit> benefitList = benefitRepository.findByCardId(card.getCardId());
            adminCardListDTO.setBenefitList(benefitList);

            // 연회비 정보
            List<AnnualFee> annualFeeList = annualFeeRepository.findByCardId(card.getCardId());
            adminCardListDTO.setAnnualFeeList(annualFeeList);

            adminCardListDTOS.add(adminCardListDTO);
        }

        return cardRepository.findCardAllBySearch(st, kw, pageable);
    }
    */

    public Page<AdminCardListDTO> getCardWithInfoAllBySearch(String searchType, String keyword, int page, int size) {
        // 검색어/타입 공백 처리
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);

        // DB에서 검색 + 페이징된 카드 목록 가져오기
        Page<CardDTO> cardPage = cardRepository.findCardAllBySearch(st, kw, pageable);

        // 각 카드에 혜택 / 연회비 정보 붙이기
        List<AdminCardListDTO> adminCardList = new ArrayList<>();

        for (CardDTO cardDTO : cardPage.getContent()) {
            AdminCardListDTO dto = new AdminCardListDTO();

            // 카드 기본정보
            dto.setCard(cardDTO);

            // 혜택 목록
            List<Benefit> benefits = benefitRepository.findByCard_CardId(cardDTO.getCardId());
            dto.setBenefitList(benefits);

            // 연회비 목록
            List<AnnualFee> annualFees = annualFeeRepository.findByCard_CardId(cardDTO.getCardId());
            dto.setAnnualFeeList(annualFees);

            adminCardList.add(dto);
        }

        // Page 객체로 다시 감싸서 반환 (검색결과, 페이지 수 그대로 유지)
        return new PageImpl<>(adminCardList, pageable, cardPage.getTotalElements());
    }

    // 카드 등록
    @Transactional
    public void registerCard(CardRequestDTO cardRequestDTO){

        // 카드
        Card card = Card.builder()
                .name(cardRequestDTO.getName())
                .engName(cardRequestDTO.getEngName())
                .type(cardRequestDTO.getType())
                .isCompany(cardRequestDTO.getIsCompany())
                .description(cardRequestDTO.getDescription())
                .status("활성")
                .build();

        System.out.println(card.toString());
        Card savedCard = cardRepository.save(card);

        // 연회비
        for(int i = 0; i<cardRequestDTO.getAnnualFeeName().size(); i++) {
            AnnualFee annualFee = AnnualFee.builder()
                                                    .annualName(cardRequestDTO.getAnnualFeeName().get(i))
                                                    .card(savedCard)
                                                    .build();

            System.out.println(annualFee.toString());

            annualFeeRepository.save(modelMapper.map(annualFee, AnnualFee.class));
        }

        // 혜택
        for(int i = 0; i<cardRequestDTO.getCategory().size(); i++){
            Benefit benefit = Benefit.builder()
                    .benefitType(cardRequestDTO.getBenefitType().get(i))
                    .benefitCategory(String.valueOf(cardRequestDTO.getCategory().get(i)))
                    .unit(cardRequestDTO.getUnit().get(i))
                    .value(cardRequestDTO.getValue().get(i))
                    .limit(cardRequestDTO.getLimit().get(i))
                    .benefitDescription(cardRequestDTO.getBenefitDescription().get(i))
                    .card(savedCard)
                    .build();

            System.out.println(i + "번째" + benefit.toString());

            benefitRepository.save(benefit);
        }
    }

}
