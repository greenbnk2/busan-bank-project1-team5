package kr.co.wave.service.config;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.config.BasicConfigDTO;
import kr.co.wave.dto.config.SiteInfoDTO;
import kr.co.wave.entity.config.SiteInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicConfigService {

    private final SiteInfoService siteInfoService;
    private final CompanyInfoService companyInfoService;
    private final CustomerServiceInfoService customerServiceInfoService;
    private final ModelMapper modelMapper;

    @Transactional
    public BasicConfigDTO getBasicConfig() {
        BasicConfigDTO dto = new BasicConfigDTO(
            siteInfoService.getSiteRecentlyOne(),
            companyInfoService.getCompanyInfoSingle(),
            customerServiceInfoService.getCustomerServiceInfoSingle()
        );

        return dto;
    }

    // 설정 업데이트
    @Transactional
    public void updateConfig(SiteInfoDTO siteInfoDTO) {
        siteInfoService.updateSiteInfo(siteInfoDTO);
    }

}
