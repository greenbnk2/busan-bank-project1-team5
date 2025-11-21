package kr.co.wave.repository.admin;

import kr.co.wave.dto.admin.AdminDTO;
import kr.co.wave.dto.card.CardDTO;
import kr.co.wave.entity.admin.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByadminId(String adminId);
    boolean existsByadminId(String adminId);

    @Query(
            value = """
                      select new kr.co.wave.dto.admin.AdminDTO(
                         a.adminId, a.password, a.name,  a.role
                      )
                      from Admin a
                      where
                        (:keyword is null or :keyword = '')
                        or (
                          ( :searchType is null or :searchType = '' ) and (
                            lower(a.name)    like lower(concat('%', :keyword, '%')) or
                            lower(a.role)  like lower(concat('%', :keyword, '%'))
                          )
                        )
                        or (:searchType = 'name'    and lower(a.name)    like lower(concat('%', :keyword, '%')))
                        or (:searchType = 'role'  and lower(a.role)  like lower(concat('%', :keyword, '%')))
                      order by a.adminId desc
                    """
    )
    Page<AdminDTO> findAdminAllBySearch(@Param("searchType") String searchType,
                                       @Param("keyword") String keyword,
                                       Pageable pageable);
}
