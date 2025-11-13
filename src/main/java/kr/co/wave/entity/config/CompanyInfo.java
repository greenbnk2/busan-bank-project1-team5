package kr.co.wave.entity.config;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_COMPANY_INFO")
public class CompanyInfo {

    // 기업 정보 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMPANY_ID")
    private int companyId;

    // 기업 정보
    @Column(name="COMPANY_NAME")
    private String companyName;          // 상호명
    @Column(name="CEO_NAME")
    private String ceoName;              // 대표이사
    @Column(name="BUSINESS_NUMBER")
    private String businessNumber;       // 사업자등록번호
    @Column(name="MAIL_ORDER_NUMBER")
    private String mailOrderNumber;      // 통신판매업신고

    @Column(name="ZIP_CODE")
    private String zipCode;         // 우편번호
    @Column(name="BASIC_ADDRESS")
    private String basicAddress;         // 기본주소
    @Column(name="DETAIL_ADDRESS")
    private String detailedAddress;      // 상세주소

}