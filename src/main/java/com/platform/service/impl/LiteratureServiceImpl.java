package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.LiteratureMaterialMapper;
import com.platform.model.LiteratureMaterial;
import com.platform.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiteratureServiceImpl implements LiteratureService {
    @Autowired
    LiteratureMaterialMapper literatureMaterialMapper;

    /**
     * 通过文献ID查询文献
     *
     * @param literatureId
     * @return
     */
    @Override
    public RestResponse literatureQuery(String literatureId) {
        LiteratureMaterial literatureMaterial = literatureMaterialMapper.selectByPrimaryKey(Integer.valueOf(literatureId));
        return ResultUtil.success(literatureMaterial);
    }
}
