package kr.co.wave.service.card;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.approval.CardApprovalDTO;
import kr.co.wave.dto.card.*;
import kr.co.wave.entity.approval.CardApproval;
import kr.co.wave.entity.card.AnnualFee;
import kr.co.wave.entity.card.Benefit;
import kr.co.wave.entity.card.Card;
import kr.co.wave.repository.approval.CardApprovalRepository;
import kr.co.wave.repository.card.AnnualFeeRepository;
import kr.co.wave.repository.card.BenefitRepository;
import kr.co.wave.repository.card.CardRepository;
import kr.co.wave.service.board.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final AnnualFeeRepository annualFeeRepository;
    private final BenefitRepository benefitRepository;
    private final CardApprovalRepository cardApprovalRepository;
    private final ModelMapper modelMapper; // Entity와 DTO를 변환해주는 객체
    private final FileUploadUtil fileUploadUtil; // 저기 util 불러오는 객체
    // 필요없는데 혹시나 남겨둠
    public Page<CardDTO> getCardAllBySearch(String searchType, String keyword, int page, int size){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findCardAllBySearch(st, kw, pageable);
    }
    
    // 필요 없어질 예정
    public CardDTO getCardById(int cardId){
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()){
            return modelMapper.map(card.get(), CardDTO.class);
        }
        return null;
    }

    // 카드 하나만 가져오기 (혜택, 연회비 포함)
    @Transactional
    public CardWithInfoDTO getCardWithInfoById(int cardId) {
        Card card = cardRepository.findById(cardId).get();

        CardWithInfoDTO cardWithInfoDTO = new CardWithInfoDTO();
        cardWithInfoDTO.setCard(modelMapper.map(card, CardDTO.class));

        List<Benefit> benefits = benefitRepository.findByCard_CardId(card.getCardId());
        cardWithInfoDTO.setBenefitList(benefits);

        List<AnnualFee> annualFees = annualFeeRepository.findByCard_CardId(card.getCardId());
        cardWithInfoDTO.setAnnualFeeList(annualFees);

        return cardWithInfoDTO;
    }


    // 카드 전체 페이지 타입으로 가져오기 (혜택, 연회비 포함) // admin 용
    @Transactional
    public Page<CardWithInfoDTO> getCardWithInfoAllBySearch(String searchType, String keyword, int page, int size) {
        // 검색어/타입 공백 처리
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);

        // DB에서 검색 + 페이징된 카드 목록 가져오기
        Page<CardDTO> cardPage = cardRepository.findCardAllBySearch(st, kw, pageable);

        // 각 카드에 혜택 / 연회비 정보 붙이기
        List<CardWithInfoDTO> adminCardList = new ArrayList<>();

        for (CardDTO cardDTO : cardPage.getContent()) {
            CardWithInfoDTO dto = new CardWithInfoDTO();

            // 카드 기본정보
            dto.setCard(cardDTO);

            // 혜택 목록
            List<Benefit> benefits = benefitRepository.findByCard_CardId(cardDTO.getCardId());
            dto.setBenefitList(benefits);

            // 추가 11.17 박효빈 혜택 카테고리 - Thymeleaf에서 처리 못하는 부분 service에서 해결
            List<String> categoryList = benefits.stream()
                    .map(Benefit::getBenefitCategory)
                    .toList();
            dto.setCategoryString(String.join(",", categoryList));


            // 연회비 목록
            List<AnnualFee> annualFees = annualFeeRepository.findByCard_CardId(cardDTO.getCardId());
            dto.setAnnualFeeList(annualFees);

            CardApprovalDTO cardApprovalDTO = cardApprovalRepository.findCardApprovalPendingByCardId(cardDTO.getCardId());
            if(cardApprovalDTO != null){
                dto.setApprovalStatus("결재진행중");
            } else dto.setApprovalStatus("");

            adminCardList.add(dto);
        }

        // Page 객체로 다시 감싸서 반환 (검색결과, 페이지 수 그대로 유지)
        return new PageImpl<>(adminCardList, pageable, cardPage.getTotalElements());
    }

    @Transactional // 사용자 페이지용 status='활성'만가져오기
    public Page<CardWithInfoDTO> getCardWithInfoAllBySearch2(String searchType, String keyword, int page, int size) {
        // 검색어/타입 공백 처리
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);

        // DB에서 검색 + 페이징된 카드 목록 가져오기
        Page<CardDTO> cardPage = cardRepository.findCardAllBySearch2(st, kw, pageable);

        // 각 카드에 혜택 / 연회비 정보 붙이기
        List<CardWithInfoDTO> adminCardList = new ArrayList<>();

        for (CardDTO cardDTO : cardPage.getContent()) {
            CardWithInfoDTO dto = new CardWithInfoDTO();

            // 카드 기본정보
            dto.setCard(cardDTO);

            // 혜택 목록
            List<Benefit> benefits = benefitRepository.findByCard_CardId(cardDTO.getCardId());
            dto.setBenefitList(benefits);

            // 추가 11.17 박효빈 혜택 카테고리 - Thymeleaf에서 처리 못하는 부분 service에서 해결
            List<String> categoryList = benefits.stream()
                    .map(Benefit::getBenefitCategory)
                    .toList();
            dto.setCategoryString(String.join(",", categoryList));


            // 연회비 목록
            List<AnnualFee> annualFees = annualFeeRepository.findByCard_CardId(cardDTO.getCardId());
            dto.setAnnualFeeList(annualFees);

            CardApprovalDTO cardApprovalDTO = cardApprovalRepository.findCardApprovalPendingByCardId(cardDTO.getCardId());
            if(cardApprovalDTO != null){
                dto.setApprovalStatus("결재진행중");
            } else dto.setApprovalStatus("");

            adminCardList.add(dto);
        }

        // Page 객체로 다시 감싸서 반환 (검색결과, 페이지 수 그대로 유지)
        return new PageImpl<>(adminCardList, pageable, cardPage.getTotalElements());
    }

    // 카드 등록
    // 카드 이미지 등록 추가 ( 11.17 박효빈)
    @Transactional
    public void registerCard(CardRequestDTO cardRequestDTO){

        // 추가 코드 ( TMI:이미지 저장하는 코드 )
        String thumbnailPath = fileUploadUtil.saveFile(cardRequestDTO.getThumbnail(),"card");
        String backgroundPath = fileUploadUtil.saveFile(cardRequestDTO.getBackground(),"card");

        // 카드
        Card card = Card.builder()
                .name(cardRequestDTO.getName())
                .engName(cardRequestDTO.getEngName())
                .type(cardRequestDTO.getType())
                .isCompany(cardRequestDTO.isCompany())
                .description(cardRequestDTO.getDescription())
                // 카드 이미지 업로드 추가 (11.17)
                .thumbnail(thumbnailPath)
                .background(backgroundPath)
                .status("비활성")
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

    // 카드 활성화
    @Transactional
    public void activateCard(int cardId){
        Optional<Card> OptionalCard = cardRepository.findById(cardId);

        if(OptionalCard.isPresent()){
            Card card = OptionalCard.get();
            card.toggleStatus("활성");
        }
    }

    // 카드 비활성화 요청
    @Transactional
    public void inactivateCard(int cardId, String reason){

        CardApproval cardApproval = CardApproval.builder().
                cardId(cardId).
                reason(reason).
                status("대기").
                build();

        cardApprovalRepository.save(cardApproval);
    }
}
