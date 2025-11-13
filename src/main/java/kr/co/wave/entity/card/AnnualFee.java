package kr.co.wave.entity.card;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_ANNUAL_FEE")
public class AnnualFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ANNUAL_FEE_ID")
    private int annualFeeId;

    @Column(name="ANNUAL_NAME")
    private String annualName;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;
}
