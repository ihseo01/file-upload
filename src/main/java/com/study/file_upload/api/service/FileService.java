package com.study.file_upload.api.service;

import com.study.file_upload.api.dto.FileUploadDto;
import com.study.file_upload.api.repository.UploadFileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String filePath;

    private final UploadFileRepository uploadFileRepository;

    @Transactional
    public void save(MultipartFile file) {
        FileUploadDto fileDto = saveFile(file);
        if (fileDto != null) {
            uploadFileRepository.save(fileDto.toEntity());
        }
    }

    // 파일 업로드
    private FileUploadDto saveFile(MultipartFile file) {
        FileUploadDto dto = null;
        if (file != null) {
            String originalName = file.getOriginalFilename();
            String saveName = UUID.randomUUID() + "_" + originalName;
            long size = file.getSize();

            try {
                // 파일 업로드
                log.info("file path : {}", filePath);
                File localFile = new File(filePath + "/" + saveName);
                file.transferTo(localFile);
                dto = FileUploadDto.builder()
                        .originalName(originalName)
                        .saveName(saveName)
                        .size(size)
                        .build();
                log.info("파일 저장 완료: {}", localFile.getCanonicalPath());
            } catch (IllegalStateException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return dto;
    }
}
