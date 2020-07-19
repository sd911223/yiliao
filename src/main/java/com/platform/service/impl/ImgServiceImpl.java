package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.exception.BusinessException;
import com.platform.service.ImgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class ImgServiceImpl implements ImgService {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImgServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${img.location}")
    private String location;

    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    @Override
    public RestResponse uploadImg(MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BusinessException("图片不能空!");
        }
        String contentType = file.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException("图片格式错误!");
        }

        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat(DATE_FORMAT).format(new Date()) + "_" + fileName;

        log.info("上传图片:name={},type={}", fileName, contentType);
        String file_name = null;
        try {
            file_name = saveImg(file, fileName);
            return ResultUtil.success(file_name);
        } catch (IOException e) {
            throw new BusinessException("上传失败!");
        }
    }

    @Override
    public ResponseEntity showPhotos(String fileName) {
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            log.info("showPhotos:" + location + fileName);
            return ResponseEntity.ok(resourceLoader.getResource("file:" + location + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public String saveImg(MultipartFile multipartFile, String filename) throws IOException {
        File file = new File(location);
        if (!file.exists()) {
            file.mkdirs();
        }

        File fileServer = new File(file, filename);
        log.info("file文件真实路径:{}", fileServer.getAbsolutePath());

        multipartFile.transferTo(fileServer);

        return filename;
    }
}
