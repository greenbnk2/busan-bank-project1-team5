package kr.co.wave.service.card;

import kr.co.wave.entity.card.History;
import kr.co.wave.repository.card.AnnualFeeRepository;
import kr.co.wave.repository.card.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper; // Entity와 DTO를 변환해주는 객체

    public List<History> getHistoryAll() {
        return historyRepository.findAll();
    }

}
