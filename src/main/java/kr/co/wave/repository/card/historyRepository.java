package kr.co.wave.repository.card;

import kr.co.wave.entity.card.Account;
import kr.co.wave.entity.card.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {

}