package kr.co.wave.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnADTO {

    private int qnaId;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createdAt;

    private boolean isAnswered;
}
