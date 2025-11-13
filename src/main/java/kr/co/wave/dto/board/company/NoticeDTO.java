package kr.co.wave.dto.board.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {

    private int noticeId;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
