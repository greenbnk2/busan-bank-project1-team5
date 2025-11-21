package kr.co.wave.entity.card;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_HISTORY")
public class History {

    // 거래 내역 Entity 정의
    // Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HISTORY_ID")
    private int historyId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Column(name="BRANCH_NAME")
    private String branchName;

    @Column(name="LATITUTE")
    private BigDecimal latitude;

    @Column(name="LONGTITUDE")
    private BigDecimal longtitude;

    @Column(name="PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name="PAYMENT_TYPE")
    private String paymentType;

    @Column(name="AMOUNT")
    private BigDecimal amount;

    @Column(name="BALANCE")
    private BigDecimal balance;

    @Column(name="PAYMENT_AT")
    private LocalDateTime paymentAt;
}
