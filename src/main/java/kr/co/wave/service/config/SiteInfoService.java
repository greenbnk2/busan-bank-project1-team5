package kr.co.wave.service.config;

import kr.co.wave.dto.config.SiteInfoDTO;
import kr.co.wave.entity.config.SiteInfo;
import kr.co.wave.repository.config.SiteInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteInfoService {

    private final SiteInfoRepository siteInfoRepository;
    private final ModelMapper modelMapper;

    public List<SiteInfoDTO> getSiteInfoAll(){
        List<SiteInfoDTO> siteInfoDTOS = new ArrayList<>();

        List<SiteInfo> siteInfoList = siteInfoRepository.findAll();
        for(SiteInfo siteInfo : siteInfoList){
            siteInfoDTOS.add(modelMapper.map(siteInfo, SiteInfoDTO.class));
        }

        return siteInfoDTOS;
    }

    public SiteInfoDTO getSiteRecentlyOne(){
        return modelMapper.map(siteInfoRepository.findTopByOrderByCreatedAtDesc(), SiteInfoDTO.class);
    }

    public SiteInfoDTO getSiteInfoById(int siteId){
        return  modelMapper.map(siteInfoRepository.findById(siteId).get(), SiteInfoDTO.class);
    }

    public void updateSiteInfo(SiteInfoDTO siteInfoDTO){
        siteInfoRepository.save(modelMapper.map(siteInfoDTO, SiteInfo.class));
    }
}
