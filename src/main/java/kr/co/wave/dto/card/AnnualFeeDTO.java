package kr.co.wave.dto.card;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnualFeeDTO {

    private int annualFeeId;

    private String annualName;

    private String cardId;
}
