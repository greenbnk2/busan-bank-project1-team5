package kr.co.wave.service.board.util;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Function;

@Component
public class DTOConverter {

    public <T> Page<T> convert(Page<Object[]> result, Function<Object[], T> mapper) {
        return result.map(mapper);
    }

    // CLOB을 String으로 변환
    public String clobToString(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Clob) {
            try {
                Clob clob = (Clob) obj;
                return clob.getSubString(1, (int) clob.length());
            } catch (SQLException e) {
                throw new RuntimeException("CLOB 변환 실패", e);
            }
        }
        return (String) obj;
    }

    // Timestamp를 LocalDateTime으로 변환
    public LocalDateTime toLocalDateTime(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Timestamp) {
            return ((Timestamp) obj).toLocalDateTime();
        }
        return (LocalDateTime) obj;
    }
}
