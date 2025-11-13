package kr.co.wave.repository.card;

import kr.co.wave.dto.card.CardDTO;
import kr.co.wave.entity.card.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(
            value = """
                      select new kr.co.wave.dto.card.CardDTO(
                         c.cardId, c.name, c.engName, c.type, c.isCompany, c.description, c.thumbnail, c.background, c.status, c.createdAt, c.updatedAt
                      )
                      from Card c
                      where
                        (:keyword is null or :keyword = '')
                        or (
                          ( :searchType is null or :searchType = '' ) and (
                            lower(c.name)    like lower(concat('%', :keyword, '%')) or
                            lower(c.engName)  like lower(concat('%', :keyword, '%')) or
                            lower(c.type) like lower(concat('%', :keyword, '%'))
                          )
                        )
                        or (:searchType = 'name'    and lower(c.name)    like lower(concat('%', :keyword, '%')))
                        or (:searchType = 'engName'  and lower(c.engName)  like lower(concat('%', :keyword, '%')))
                        or (:searchType = 'type' and lower(c.type) like lower(concat('%', :keyword, '%')))
                      order by c.cardId desc
                    """
    )
    Page<CardDTO> findCardAllBySearch(@Param("searchType") String searchType,
                                      @Param("keyword") String keyword,
                                      Pageable pageable);
}
