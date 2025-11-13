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
public class InvestorInfoDTO {

    private int eventId;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
