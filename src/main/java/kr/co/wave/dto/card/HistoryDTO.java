package kr.co.wave.dto.card;

import jakarta.persistence.*;
import kr.co.wave.entity.card.Account;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDTO {

    private int historyId;

    private String account;

    private String branchName;

    private BigDecimal latitude;

    private BigDecimal longtitude;

    private String paymentMethod;

    private String paymentType;

    private BigDecimal amount;

    private BigDecimal balance;

    private LocalDateTime paymentAt;
}
