package kr.co.wave.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitDTO {

    private int benefitId;

    private String type; // '포인트 적립', '할인', '캐시백' 등

    private String category; // '쇼핑', '음식', '주유', '영화' 등 혜택 이름 <- 관리자의 분류체계 관리에서

    private String unit;

    private int value;

    private int limit;

    private String description;

    private String cardId;
}
