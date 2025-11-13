package kr.co.wave.repository.board.company;

import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.entity.board.company.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    @Query(
        value = """
                  SELECT n.notice_id, n.title, n.content, n.writer, n.created_at
                  FROM TB_COMPANY_NOTICE n
                  WHERE
                      (:keyword is null or :keyword = '')
                      or (
                        ( :searchType is null or :searchType = '' ) and (
                          lower(n.title) like lower('%' || :keyword || '%') or
                          DBMS_LOB.INSTR(lower(n.content), lower(:keyword), 1, 1) > 0 or
                          lower(n.writer) like lower('%' || :keyword || '%')
                        )
                      )
                      or (:searchType = 'title' and lower(n.title) like lower('%' || :keyword || '%'))
                      or (:searchType = 'content' and DBMS_LOB.INSTR(lower(n.content), lower(:keyword), 1, 1) > 0)
                      or (:searchType = 'writer' and lower(n.writer) like lower('%' || :keyword || '%'))
                """,
        nativeQuery = true
     )
    Page<Object[]> findNoticeBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword, Pageable pageable);

    // 최신글 5개 찾아오기
    List<Notice> findTop5ByOrderByCreatedAtDesc();
}