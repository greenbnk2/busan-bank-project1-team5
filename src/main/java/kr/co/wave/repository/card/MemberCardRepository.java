package kr.co.wave.repository.card;

import jakarta.persistence.EntityManager;
import kr.co.wave.entity.card.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MemberCardRepository extends JpaRepository<Account, Integer> {

}