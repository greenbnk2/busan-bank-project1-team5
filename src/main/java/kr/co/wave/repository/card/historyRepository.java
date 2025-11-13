package kr.co.wave.repository.card;

import kr.co.wave.entity.card.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface historyRepository extends JpaRepository<Account, Integer> {

}