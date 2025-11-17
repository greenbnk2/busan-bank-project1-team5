package kr.co.wave.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface signup extends JpaRepository<signup,String> {
    Optional<signup> findByMemId(String memId);
}
