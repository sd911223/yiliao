package com.platform.service;

import com.platform.common.RestResponse;

public interface LiteratureService {
    /**
     * 通过文献ID查询文献
     *
     * @param literatureId
     * @return
     */
    RestResponse literatureQuery(String literatureId);
}
