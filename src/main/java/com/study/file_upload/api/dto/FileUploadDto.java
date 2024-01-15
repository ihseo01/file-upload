package com.study.file_upload.api.dto;

import com.study.file_upload.entity.UploadFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileUploadDto {

    private Long id; // 파일 번호 (PK)
    private final String originalName; // 원본 파일명
    private final String saveName; // 저장 파일명
    private final long size; // 파일 크기

    @Builder
    public FileUploadDto(String originalName, String saveName, long size) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
    }

    public UploadFile toEntity() {
        return UploadFile.builder()
                .originalName(originalName)
                .saveName(saveName)
                .size(size)
                .build();
    }
}
