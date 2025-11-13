package kr.co.wave.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ImageHandler {

    @Value("${file.upload.path}")
    private String uploadPath;

    // 하위 폴더 포함하여 이미지 조회
    public Resource getImage(String subFolder, String imageName) {
        Path imagePath = Paths.get(uploadPath, subFolder, imageName);
        return new FileSystemResource(imagePath);
    }

    // 기존 메서드 (하위 호환성 유지)
    public Resource getProductImage(String imageName) {
        return getImage("product", imageName);
    }

    // 하위 폴더를 지정하여 파일 저장
    public Map<String, String> saveFiles(List<MultipartFile> files, String subFolder) throws RuntimeException {
        if(files == null || files.isEmpty()){
            return null;
        }

        // 하위 폴더 경로 생성
        Path subFolderPath = Paths.get(uploadPath, subFolder);
        try {
            // 폴더가 없으면 생성
            if (!Files.exists(subFolderPath)) {
                Files.createDirectories(subFolderPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("폴더 생성 실패: " + e.getMessage());
        }

        Map<String, String> uploadNames = new HashMap<>();
        for (MultipartFile multipartFile : files) {
            String fieldName = multipartFile.getName();
            String originalFilename = multipartFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString() + ext;

            // 하위 폴더 포함한 전체 경로
            Path savePath = Paths.get(uploadPath, subFolder, savedName);

            try {
                Files.copy(multipartFile.getInputStream(), savePath);
                // 하위 폴더 포함한 경로를 반환 (예: config/uuid.jpg)
                uploadNames.put(fieldName, subFolder + "/" + savedName);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return uploadNames;
    }

    // 기존 메서드 (하위 호환성 유지)
    public Map<String, String> saveFiles(List<MultipartFile> files) throws RuntimeException {
        return saveFiles(files, "");
    }
}