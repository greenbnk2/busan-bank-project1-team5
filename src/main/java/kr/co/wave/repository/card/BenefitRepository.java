package kr.co.wave.repository.card;

import kr.co.wave.entity.card.Account;
import kr.co.wave.entity.card.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitRepository extends JpaRepository<Benefit, Integer> {
    List<Benefit> findByCard_CardId(Integer cardId);
}