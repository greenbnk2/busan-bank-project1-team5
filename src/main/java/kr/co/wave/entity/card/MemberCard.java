package kr.co.wave.entity.card;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_MEMBER_CARD")
public class MemberCard {

    // 고객이 가입한 카드 상품 목록 저장 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_CARD_ID")
    private int memberCardId;

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDate createdAt; // 카드 발급일

    @Column(name="UPDATED_AT")
    private LocalDate updatedAt; // 카드 만료일

}
