package kr.co.wave.entity.card;

import jakarta.persistence.*;
import kr.co.wave.entity.member.Member;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_BENEFIT")
public class Benefit {

    // 혜택 테이블 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BENEFIT_ID")
    private int benefitId;

    @Column(name="TYPE")
    private String benefitType; // '포인트 적립', '할인', '캐시백' 등

    @Column(name="CATEGORY")
    private String benefitCategory; // '쇼핑', '음식', '주유', '영화' 등 혜택 이름 <- 관리자의 분류체계 관리에서

    @Column(name="UNIT")
    private String unit;

    @Column(name="value")
    private int value;

    @Column(name="limit")
    private int limit;

    @Column(name="description")
    private String benefitDescription;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;
}