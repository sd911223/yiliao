package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.entity.req.CollectReq;
import com.platform.model.UserInfo;
import com.platform.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public RestResponse listCollect(@LoginUser UserInfo userInfo,
                                    @ApiParam("名字排序") @RequestParam(value = "nameAsc",required = false)String nameAsc,
                                    @ApiParam("时间排序") @RequestParam(value = "timeAsc",required = false)String timeAsc) {

        return collectService.listCollect(userInfo,nameAsc,timeAsc);

    }


    /**
     * 删除收藏
     *
     * @param collectId
     * @return
     */
    @ApiOperation("删除收藏")
    @GetMapping("/collect/delete")
    public RestResponse deleteCollect(@ApiParam("收藏ID") @RequestParam(value = "collectId") String collectId) {

        return collectService.deleteCollect(Integer.valueOf(collectId));

    }

    /**
     * 修改备注
     *
     * @param collectId
     * @return
     */
    @ApiOperation("修改备注")
    @GetMapping("/collect/update")
    public RestResponse updateCollect(@ApiParam("收藏ID") @RequestParam(value = "collectId") String collectId, @ApiParam("备注") @RequestParam(value = "remark") String remark) {

        return collectService.updateCollect(Integer.valueOf(collectId),remark);

    }
}
