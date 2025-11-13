package kr.co.wave.entity.config;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_CUSTOMER_SERVICE_INFO")
public class CustomerServiceInfo {

    // 고객센터 정보 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CS_ID")
    private int csId;

    // 고객센터 정보
    @Column(name="CUSTOMER_PHONE")
    private String customerPhone;        // 전화번호

    @Column(name="CUSTOMER_HOURS")
    private String customerHours;        // 업무시간

    @Column(name="CUSTOMER_EMAIL")
    private String customerEmail;        // 이메일

    @Column(name="DISPUTE_CONTACT")
    private String disputeContact;       // 전자금융거래 분쟁담당
}