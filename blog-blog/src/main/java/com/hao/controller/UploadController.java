package com.hao.controller;

import com.hao.domain.ResponseResult;
import com.hao.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(MultipartFile img) {
        return ResponseResult.okResult(uploadService.uploadImg(img));
    }
}