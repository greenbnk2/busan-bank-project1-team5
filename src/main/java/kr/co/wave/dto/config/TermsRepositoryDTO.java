package kr.co.wave.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermsRepositoryDTO {

    private int termsId;

    private String type; // 유형

    private String category;

    private String title; // 제목

    private String content; // 내용

    private String version;	// 버전 (예: v1.0, v1.1)

    private boolean isRequired; // 필수 여부

    private LocalDate createdAt; // 생성일

    private LocalDate updatedAt; // 수정일

    private String pdfFile;

    private String originalName;

    private String termStatus;
}
