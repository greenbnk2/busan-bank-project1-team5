package kr.co.wave.repository.config;

import kr.co.wave.entity.config.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
