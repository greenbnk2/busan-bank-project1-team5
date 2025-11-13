package kr.co.wave.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteInfoDTO {

    private int siteId;

    private String version;

    private String title;   // 사이트 제목

    private String subtitle; // 부제목

    private String headerLogo;

    private String footerLogo;

    private String favicon;

    private String copyright;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}