package kr.co.wave.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {

    private int cardId;

    private String name; // 카드 이름

    private String engName; // 카드 영어 이름

    private String type; // 신용, 체크 구분

    private boolean isCompany; // 기업카드 구분

    private String description; // 카드 설명

    private String thumbnail; // 카드 썸네일

    private String background; // 카드 배경

    private String status;

    private LocalDate createdAt; // 카드 발급일

    private LocalDate updatedAt; // 카드 만료일


}
