package com.hao.service;

import com.hao.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadImg(MultipartFile img);

    ResponseResult uploadRandomImg();
}
