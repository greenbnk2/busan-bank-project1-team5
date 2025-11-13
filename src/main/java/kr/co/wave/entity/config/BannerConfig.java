package kr.co.wave.entity.config;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BannerConfig {

    // 배너 관리 Entity 정의
    // 각 Entity마다 Repository, Service 정의해두면 편함.
    // Entity의 정석은 일관성을 유지하기 위해 Setter를 정의하지 않음. 하지만 데이터를 변경해야 할 일이 있기 때문에 같은 형태의 DTO도 선언.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 배너 이미지 파일명 또는 URL
    @Column(nullable = false)
    private String imagePath;

    // 배너 정보 (설명 등)
    private String description;

    // 배너 위치 (메인상단, 메인슬라이더, 상품상세, 회원로그인, 마이페이지 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String location;

    // 배너 노출 기간
    private LocalDate startDate;
    private LocalDate endDate;

    // 배너 노출 시간
    private LocalTime startTime;
    private LocalTime endTime;

    // 관리용 (작성자, 수정자 등)
    private String createdBy;
    private String updatedBy;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }

    // getter / setter 생략
}