package com.platform.controller;


import com.platform.common.RestResponse;
import com.platform.service.ImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shitou
 * @Description 图片管理
 */
@RestController
@RequestMapping(value = "mtApi/")
@Api(tags = "图片管理")
public class ImgManageController {
    @Autowired
    ImgService imgService;

    @PostMapping("/file/upload")
    @ApiOperation("上传")
    public RestResponse uploadFile(@RequestParam("file") MultipartFile file) {

        return imgService.uploadImg(file);
    }

    @GetMapping("/file/show")
    @ApiOperation("显示图片")
    public ResponseEntity showPhotos(@RequestParam("fileName") String fileName) {

        return imgService.showPhotos(fileName);
    }
}
