package kr.co.wave.repository.config;

import kr.co.wave.dto.approval.CardApprovalDTO;
import kr.co.wave.dto.config.TermsDTO;
import kr.co.wave.dto.config.TermsRepositoryDTO;
import kr.co.wave.entity.config.CustomerServiceInfo;
import kr.co.wave.entity.config.Terms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TermsRepository extends JpaRepository<Terms, Integer> {

    @Query(
            value = """
                      select new kr.co.wave.dto.config.TermsRepositoryDTO(
                        t.termsId, t.type, t.category, t.title, t.content, t.version, t.isRequired, t.createdAt, t.updatedAt, t.pdfFile, t.originalName, t.termStatus           
                      )
                      from Terms t
                      where
                        (:keyword is null or :keyword = '')
                        or (
                          ( :searchType is null or :searchType = '' ) and (
                            lower(t.title)    like lower(concat('%', :keyword, '%')) or
                            lower(t.content)  like lower(concat('%', :keyword, '%')) or
                            lower(t.originalName) like lower(concat('%', :keyword, '%'))
                          )
                        )
                        or (:searchType = 'title'    and lower(t.title)    like lower(concat('%', :keyword, '%')))
                        or (:searchType = 'content'  and lower(t.content)  like lower(concat('%', :keyword, '%')))
                        or (:searchType = 'originalName' and lower(t.originalName) like lower(concat('%', :keyword, '%')))
                      order by t.termsId desc
                    """
    )
    Page<TermsRepositoryDTO> findTermsAllBySearch(@Param("searchType") String searchType,
                                                  @Param("keyword") String keyword,
                                                  Pageable pageable);


}
