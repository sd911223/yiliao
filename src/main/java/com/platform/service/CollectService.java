package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.req.CollectReq;
import com.platform.model.UserInfo;

/**
 * @author shitou
 */
public interface CollectService {
    /**
     * 添加收藏
     *
     * @param collectReq
     * @return
     */
    RestResponse addCollect(UserInfo userInfo, CollectReq collectReq);

    /**
     * 用户收藏文件
     *
     * @param userInfo
     * @return
     */
    RestResponse listCollect(UserInfo userInfo);

    /**
     * 删除收藏
     *
     * @param id
     * @return
     */
    RestResponse deleteCollect(Integer id);
}
