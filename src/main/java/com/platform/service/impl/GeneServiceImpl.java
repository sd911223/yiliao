package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.platform.service.GeneDao;
import com.platform.service.GeneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shitou
 */
@Service
@Slf4j
public class GeneServiceImpl implements GeneService {
    @Autowired
    private GeneDao geneDao;

    @Override
    public Map<String, Object> getGeneByOmimId(String omimId) {
        // TODO Auto-generated method stub
        Map<String, String> result = geneDao.getGeneByOmimId(omimId);
        //把结果符合json格式
        Map<String, Object> cleanResult = this.getJsonResult(result);
        return cleanResult;
    }

    @Override
    public Map<String, Object> getGeneByEntrezId(String EntrezId) {
        // TODO Auto-generated method stub
        Map<String, String> result = geneDao.getGeneByEntrezId(EntrezId);
        //把结果符合json格式
        Map<String, Object> cleanResult = this.getJsonResult(result);
        return cleanResult;
    }

    @Override
    public Map<String, Object> getGeneByGeneSymbol(String geneSymbol) {
        // TODO Auto-generated method stub
        Map<String, String> result = geneDao.getGeneByGeneSymbol(geneSymbol);
        //把结果符合json格式
        Map<String, Object> cleanResult = this.getJsonResult(result);
        return cleanResult;
    }

    /**
     * json格式化方法
     */
    private Map<String, Object> getJsonResult(Map<String, String> result) {
        Map<String, Object> cleanResult = new HashMap<String, Object>(result.size());

        for (String key : result.keySet()) {
            /*单引号处理*/
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
