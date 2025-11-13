package kr.co.wave.entity.card;

import jakarta.persistence.*;
import kr.co.wave.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_CARD")
public class Card {
    
    // 카드 상품 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CARD_ID")
    private int cardId; // ID

    @Column(name="NAME")
    private String name; // 카드 이름

    @Column(name="ENG_NAME")
    private String engName; // 카드 영어 이름

    @Column(name="TYPE")
    private String type; // 신용, 체크 구분

    @Column(name="IS_COMPANY")
    private boolean isCompany; // 기업카드 구분
   
    @Column(name="DESCRIPTION")
    private String description; // 카드 설명
    
    @Column(name="THUMBNAIL")
    private String thumbnail; // 카드 썸네일

    @Column(name="BACKGROUND")
    private String background; // 카드 배경

    @Column(name="status")
    private String status; // 카드 상태

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDate createdAt; // 카드 발급일

    @Column(name="UPDATED_AT")
    private LocalDate updatedAt; // 카드 만료일

}
