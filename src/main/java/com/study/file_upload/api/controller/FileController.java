package com.study.file_upload.api.controller;

import com.study.file_upload.api.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@Slf4j
public class FileController {
    private final FileService fileService;

    @PostMapping("/fileUpload")
    public ResponseEntity<?> fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile) {
        log.info("FileController.fileUpload()");
        fileService.save(uploadFile);
        return ResponseEntity.ok("File upload OK");
    }
}
