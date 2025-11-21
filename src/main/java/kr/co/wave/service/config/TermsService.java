package kr.co.wave.service.config;


import jakarta.transaction.Transactional;
import kr.co.wave.dto.config.TermsDTO;
import kr.co.wave.dto.config.TermsRepositoryDTO;
import kr.co.wave.entity.config.Terms;
import kr.co.wave.repository.config.TermsRepository;
import kr.co.wave.service.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsService {

    @Value("${file.upload.path}")
    private String uploadPath;

    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;
    private final FileUploadUtil fileUploadUtil;

    public List<TermsRepositoryDTO> getTermsAll(){

        return null; // termsRepository.findAll();
    }

    // 약관 등록
    public void registerTerms(TermsDTO termsDTO){
        String originalName = termsDTO.getPdfFile().getOriginalFilename(); // 파일 원래 이름
        String pdfPath = fileUploadUtil.saveFile(termsDTO.getPdfFile(),"terms"); // 저장할 상대 경로 반환 , terms = 저장할 폴더 이름

        Terms terms = Terms.builder().
                title(termsDTO.getTitle()).
                content(termsDTO.getContent()).
                isRequired(termsDTO.isRequired()).
                pdfFile(pdfPath).
                originalName(originalName).
                build();

        termsRepository.save(terms);
    }

    public ResponseEntity<Resource> fileDownload(int termsId){
        Terms term = termsRepository.findById(termsId).orElse(null);

        try {
            // 전체 경로 생성: uploadPath + term.getPdfFile()
            Path filePath = Paths.get(uploadPath, term.getPdfFile());

            System.out.println("전체 경로: " + filePath.toAbsolutePath());
            System.out.println("파일 존재: " + Files.exists(filePath));

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String filename = filePath.getFileName().toString();

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + URLEncoder.encode(filename, StandardCharsets.UTF_8) + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("파일을 찾을 수 없습니다: " + filePath.toAbsolutePath());
            }
        } catch (Exception e) {
            throw new RuntimeException("파일 다운로드 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Page<TermsRepositoryDTO> getTermsAllBySearch(String searchType, String keyword, int page, int size) {
        // 검색어/타입 공백 처리
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);

        return termsRepository.findTermsAllBySearch(searchType, keyword, pageable);
    }

    public void updateTerms(TermsDTO termsDTO){

        termsDTO.setContent(termsDTO.getContent());
        termsDTO.setIsRequired(termsDTO.isRequired());
        termsDTO.setUpdatedAt(termsDTO.getUpdatedAt());

        termsRepository.save(modelMapper.map(termsDTO, Terms.class));
    }
}
