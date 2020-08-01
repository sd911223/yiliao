package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.entity.req.CollectReq;
import com.platform.model.UserInfo;
import com.platform.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "mtApi/")
@Api(tags = "文件夹收藏")
public class CollectController {
    @Autowired
    CollectService collectService;

    /**
     * 添加收藏信息
     *
     * @param collectReq
     * @param userInfo
     * @return
     */
    @ApiOperation("添加收藏信息")
    @PostMapping("/collect/add")
    public RestResponse addCollect(@LoginUser UserInfo userInfo, @RequestBody CollectReq collectReq) {

        return collectService.addCollect(userInfo, collectReq);

    }

    /**
     * 用户收藏信息
     *
     * @param userInfo
     * @return
     */
    @ApiOperation("用户收藏信息")
    @PostMapping("/collect/list")
    public RestResponse listCollect(@LoginUser UserInfo userInfo) {

        return collectService.listCollect(userInfo);

    }


    /**
     * 删除收藏
     *
     * @param id
     * @return
     */
    @ApiOperation("删除收藏")
    @PostMapping("/collect/delete")
    public RestResponse deleteCollect(@RequestParam(value = "id") Integer id) {

        return collectService.deleteCollect(id);

    }
}
