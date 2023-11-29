package com.hao.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.hao.domain.ResponseResult;
import com.hao.enums.AppHttpCodeEnum;
import com.hao.exception.SystemException;
import com.hao.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //根据后缀判断文件类型
        int index = originalFilename.lastIndexOf('.');
        //有没有
        String suffix = null;
        if (index == -1 || (suffix = originalFilename.substring(index + 1)).isEmpty()) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //对不对
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
        if (!allowSuffix.contains(suffix)) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

//生成文件名
        //根据日期生成路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //后缀和文件后缀一致
        String filePath = new StringBuilder().append(datePath).append(uuid).append(".").append(suffix).toString();


        String url = uploadOss(img, filePath);
        return ResponseResult.okResult(url);
    }

    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.bucketName}")
    private String bucketName;

    private String ALI_DOMAIN = "https://benhao-test.oss-cn-guangzhou.aliyuncs.com/";

    private String uploadOss(MultipartFile imgFile, String filePath) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, filePath, imgFile.getInputStream());
            return ALI_DOMAIN + filePath;
        } catch (Exception e) {
            throw new SystemException(AppHttpCodeEnum.FILE_UPLOAD_ERROR);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
