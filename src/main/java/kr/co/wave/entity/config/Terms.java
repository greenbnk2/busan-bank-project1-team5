package kr.co.wave.entity.config;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TB_TERMS")
public class Terms {

    // 약관 정보 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERMS_ID")
    private int termsId;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name="TYPE")
    private String type; // 유형

    @Column(name = "TITLE")
    private String title; // 제목

    @Column(name = "CONTENT")
    private String content; // 내용

    @Column(name = "VERSION")
    private String version;	// 버전 (예: v1.0, v1.1)

    @Column(name = "IS_REQUIRED")
    private boolean isRequired; // 필수 여부

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDate createdAt; // 생성일

    @Column(name="UPDATED_AT")
    private LocalDate updatedAt; // 수정일
}
