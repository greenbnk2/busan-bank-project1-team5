package kr.co.wave.dto.config;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermsDTO {

    private int termsId;

    private String type; // 유형

    private String category;

    private String title; // 제목

    private String content; // 내용

    private String version;	// 버전 (예: v1.0, v1.1)

    private boolean isRequired; // 필수 여부

    private LocalDate createdAt; // 생성일

    private LocalDate updatedAt; // 수정일
}
