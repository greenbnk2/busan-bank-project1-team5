package kr.co.wave.repository.config;

import kr.co.wave.entity.config.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteInfoRepository extends JpaRepository<SiteInfo, Integer> {

    SiteInfo findTopByOrderByCreatedAtDesc();
}