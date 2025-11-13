package kr.co.wave.dto.config;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CompanyInfoDTO {

    private int companyId;

    private String companyName;          // 상호명

    private String ceoName;              // 대표이사

    private String businessNumber;       // 사업자등록번호

    private String mailOrderNumber;      // 통신판매업신고

    private String zipCode;         // 우편번호

    private String basicAddress;         // 기본주소

    private String detailedAddress;      // 상세주소

}