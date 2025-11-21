package kr.co.wave.service.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
public class PDFUtil {

    @Value("${file.upload.path}")
    private String uploadDir;

    public String saveFile(MultipartFile file, String subDir) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            // 1) 프로젝트 절대 경로
            String baseDir = System.getProperty("user.dir");

            // 2) 기본 업로드 폴더 (예: C:/project-root/uploads)
            String fullPath = baseDir + File.separator + uploadDir;

            // 3) 하위 폴더 지정 시 (예: uploads/card)
            if (subDir != null && !subDir.isBlank()) {
                fullPath = fullPath + File.separator + subDir;
            }

            // 4) 폴더 없으면 생성
            File directory = new File(fullPath);
            if (!directory.exists()) {
                directory.mkdirs();
                log.info("업로드 폴더 생성: {}", directory.getAbsolutePath());
            }

            // 5) 확장자 추출
            String original = file.getOriginalFilename();
            String ext = original.substring(original.lastIndexOf("."));

            // 6) UUID 기반 파일명 생성
            String fileName = UUID.randomUUID().toString().replace("-", "") + ext;

            // 7) 최종 저장 경로
            Path savePath = Paths.get(fullPath, fileName);

            // 8) 파일 저장
            file.transferTo(savePath.toFile());
            log.info("파일 저장 완료: {}", savePath);

            // 9) DB에 저장할 상대경로 반환
            return (subDir != null && !subDir.isBlank())
                    ? subDir + "/" + fileName
                    : fileName;

        } catch (Exception e) {
            log.error("파일 저장 실패", e);
            throw new RuntimeException("파일 저장 실패: " + e.getMessage(), e);
        }
    }
}
