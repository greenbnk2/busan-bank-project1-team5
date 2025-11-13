package kr.co.wave.repository.card;

import kr.co.wave.entity.card.AnnualFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnualFeeRepository extends JpaRepository<AnnualFee, Integer> {
    List<AnnualFee> findByCard_CardId(Integer cardId);
}