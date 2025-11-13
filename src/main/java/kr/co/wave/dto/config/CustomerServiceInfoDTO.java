package kr.co.wave.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerServiceInfoDTO {

    private int csId;

    private String customerPhone;        // 전화번호

    private String customerHours;        // 업무시간

    private String customerEmail;        // 이메일

    private String disputeContact;       // 전자금융거래 분쟁담당
}