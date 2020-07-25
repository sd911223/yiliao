package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.service.DiseaseDao;
import com.platform.service.DiseaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    DiseaseDao diseaseDao;

    @Override
    public RestResponse disease(String omimId) {
        // TODO Auto-generated method stub
        Map<String, String> result = diseaseDao.disease(omimId);
        //把结果符合json格式
        Map<String, Object> cleanResult = this.getJsonResult(result);
        return ResultUtil.success(cleanResult);
    }

    @Override
    public List<Map<String, String>> listDisease(String[] symptomArray) {
        return diseaseDao.listDisease(symptomArray);
    }


    /**
     * json格式化方法
     */
    private Map<String, Object> getJsonResult(Map<String, String> result) {
        Map<String, Object> cleanResult = new HashMap<String, Object>(result.size());

        for (String key : result.keySet()) {

            String value = result.get(key).replaceAll("\\\\'", "\\'")/*单引号处理*/;
            try {
                if (value.startsWith("[") && value.endsWith("]")) {//数组
                    cleanResult.put(key, JSON.parseArray(value));
                    continue;
                }
                if (value.startsWith("{") && value.endsWith("}")) {//对象
                    cleanResult.put(key, JSONObject.parse(value));

                    continue;

                }
            } catch (JSONException e) {//异常，存入字符串
                // TODO Auto-generated catch block
                e.printStackTrace();
                cleanResult.put(key, value);
            }
            //一般字符串
            cleanResult.put(key, value);

        }
        return cleanResult;

    }
}
