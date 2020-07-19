package com.platform.service;

import com.platform.common.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    RestResponse uploadImg(MultipartFile file);

    ResponseEntity showPhotos(String fileName);
}
