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
@Table(name = "TB_ACCOUNT")
public class Account {

    // 계좌 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    private int accountId;

    @ManyToOne
    @JoinColumn(name = "MEM_ID")
    private Member member;

}
