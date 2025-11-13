package kr.co.wave.entity.board.company;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_COMPANY_NOTICE")
public class Notice {

    // 은행 소개 - 공지 사항 Entity 정의, 은행소개는 진행하지 않지만 일단 게시판 참고용으로 냅둠.
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NOTICE_ID")
    private int noticeId;

    @Column(name="TITLE")
    private String title;

    @Lob // CLOB으로 변경
    @Column(name="CONTENT")
    private String content;

    @Column(name="WRITER")
    private String writer;

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;
}
