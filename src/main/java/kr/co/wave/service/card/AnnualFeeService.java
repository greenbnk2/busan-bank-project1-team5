package kr.co.wave.service.card;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.card.BenefitDTO;
import kr.co.wave.dto.card.CardDTO;
import kr.co.wave.dto.card.CardRequestDTO;
import kr.co.wave.entity.card.AnnualFee;
import kr.co.wave.entity.card.Benefit;
import kr.co.wave.repository.card.AnnualFeeRepository;
import kr.co.wave.repository.card.BenefitRepository;
import kr.co.wave.repository.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnualFeeService {

    private final AnnualFeeRepository annualFeeRepository;
    private final ModelMapper modelMapper; // Entity와 DTO를 변환해주는 객체

}
