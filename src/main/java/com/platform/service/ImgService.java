package com.platform.service;

import com.platform.common.RestResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    RestResponse uploadImg(MultipartFile file);
}
