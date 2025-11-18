package kr.co.wave.dto.card;

import kr.co.wave.entity.card.AnnualFee;
import kr.co.wave.entity.card.Benefit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardWithInfoDTO {
    CardDTO card;
    List<Benefit> benefitList;
    List<AnnualFee> annualFeeList;
    String approvalStatus;

    // JS 카테고리별 버튼 필터링 위해 추가
    private String categoryString;
}
