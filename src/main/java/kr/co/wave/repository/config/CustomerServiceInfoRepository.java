package kr.co.wave.repository.config;

import kr.co.wave.entity.config.CustomerServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerServiceInfoRepository extends JpaRepository<CustomerServiceInfo, Integer> {
    @Query("select cs from CustomerServiceInfo cs")
    Optional<CustomerServiceInfo> findCustomerServiceInfoSingle();
}
