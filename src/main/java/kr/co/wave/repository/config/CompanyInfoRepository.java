package kr.co.wave.repository.config;

import kr.co.wave.entity.config.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer> {

    @Query("select c from CompanyInfo c")
    Optional<CompanyInfo> findCompanyInfoSingle();
}
