package com.study.file_upload.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class FileViewController {

    @GetMapping("/file-upload")
    public String fileUpload(Model model) {
        return "fileUpload";
    }

}
