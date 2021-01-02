package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.LiteratureMapper;
import com.platform.dao.LiteratureMaterialMapper;
import com.platform.model.Literature;
import com.platform.model.LiteratureMaterial;
import com.platform.service.LiteratureService;
import com.platform.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiteratureServiceImpl implements LiteratureService {
    @Autowired
    LiteratureMaterialMapper literatureMaterialMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LiteratureMapper literatureMapper;

    /**
     * 通过文献ID查询文献
     *
     * @param literatureId
     * @return
     */
    @Override
    public RestResponse literatureQuery(String literatureId) {
        if (redisUtil.get("literatureId:" + literatureId) == null) {
            Literature literatureMaterial = literatureMapper.selectByPrimaryKey(Integer.valueOf(literatureId));
            if (literatureMaterial != null) {
                redisUtil.set("literatureId:" + literatureId, JSON.toJSONString(literatureMaterial), 60 * 10L);
            }
            return ResultUtil.success(literatureMaterial);
        } else {
            Object o = redisUtil.get("literatureId:" + literatureId);
            Literature diseaseOmimList = JSON.parseObject(o.toString(), Literature.class);
            return ResultUtil.success(diseaseOmimList);
        }
    }
}
