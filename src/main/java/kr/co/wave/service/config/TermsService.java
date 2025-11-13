package kr.co.wave.service.config;


import kr.co.wave.dto.config.TermsDTO;
import kr.co.wave.entity.config.Terms;
import kr.co.wave.repository.config.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;

    public List<TermsDTO> getTermsAll(){
        List<TermsDTO> termsDTOList = new ArrayList<>();

        List<Terms> termsList = termsRepository.findAll();
        for(Terms term : termsList){
            termsDTOList.add(modelMapper.map(term, TermsDTO.class));
        }

        return termsDTOList;
    }

    public void updateTerms(TermsDTO termsDTO){

        termsDTO.setContent(termsDTO.getContent());
        termsDTO.setRequired(termsDTO.isRequired());
        termsDTO.setUpdatedAt(termsDTO.getUpdatedAt());

        termsRepository.save(modelMapper.map(termsDTO, Terms.class));
    }
}
