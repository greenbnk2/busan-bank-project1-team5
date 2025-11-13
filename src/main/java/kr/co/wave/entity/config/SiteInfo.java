package kr.co.wave.entity.config;

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
@Table(name = "TB_SITE_INFO")
public class SiteInfo {

    // 사이트 정보 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SITE_ID")
    private int siteId;

    // 버전 정보
    @Column(name="VERSION", nullable = false)
    private String version; // ex: 0.0.1-SNAPSHOT

    // 사이트 기본 정보
    @Column(name="TITLE")
    private String title;   // 사이트 제목
    @Column(name="SUBTITLE")
    private String subtitle; // 부제목

    // 로고 정보 (파일명 혹은 URL)
    @Column(name="HEADER_LOGO")
    private String headerLogo;
    @Column(name="FOOTER_LOGO")
    private String footerLogo;
    @Column(name="FAVICON")
    private String favicon;

    // 카피라이트
    @Column(name="COPYRIGHT")
    private String copyright;

    // 생성/수정 시간
    @CreationTimestamp
    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;
}