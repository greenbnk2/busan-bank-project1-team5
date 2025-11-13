package kr.co.wave.service.config;

import jakarta.persistence.EntityNotFoundException;
import kr.co.wave.dto.config.CustomerServiceInfoDTO;
import kr.co.wave.entity.config.CustomerServiceInfo;
import kr.co.wave.repository.config.CustomerServiceInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceInfoService {

    private final CustomerServiceInfoRepository customerServiceInfoRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceInfoDTO getCustomerServiceInfoSingle() {
        CustomerServiceInfo customerServiceInfo = customerServiceInfoRepository.findCustomerServiceInfoSingle()
                .orElseThrow(() -> new EntityNotFoundException("고객센터 정보가 존재하지 않습니다."));

        return modelMapper.map(customerServiceInfo, CustomerServiceInfoDTO.class);
    }

    public void updateCustomerServiceInfo(CustomerServiceInfoDTO customerServiceInfoDTO) {
        customerServiceInfoRepository.save(modelMapper.map(customerServiceInfoDTO, CustomerServiceInfo.class));

    }
}
