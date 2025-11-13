package kr.co.wave.repository.config;

import kr.co.wave.entity.config.CustomerServiceInfo;
import kr.co.wave.entity.config.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
}
