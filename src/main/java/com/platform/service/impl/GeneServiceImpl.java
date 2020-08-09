package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.platform.dao.EntrezAnotherMapper;
import com.platform.model.EntrezAnother;
import com.platform.model.EntrezAnotherExample;
import com.platform.service.GeneDao;
import com.platform.service.GeneService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shitou
 */
@Service
@Slf4j
public class GeneServiceImpl implements GeneService {
    @Autowired
    private GeneDao geneDao;
    @Autowired
    EntrezAnotherMapper entrezAnotherMapper;

    public static String paths2 = "C:\\Users\\shidun\\Desktop\\correspondence.txt";

    @Override
    public Map<String, Object> getGeneByOmimId(String omimId) {
        // TODO Auto-generated method stub
        Map<String, String> result = geneDao.getGeneByOmimId(omimId);
        if (StringUtils.isNotBlank(result.get("gene_symbol"))) {
            EntrezAnotherExample entrezAnotherExample = new EntrezAnotherExample();
            entrezAnotherExample.createCriteria().andEntrezNameEqualTo(result.get("gene_symbol"));
            List<EntrezAnother> anotherList = entrezAnotherMapper.selectByExample(entrezAnotherExample);
            if (!anotherList.isEmpty() && anotherList.get(0).getHg38Location() != null) {
                result.put("HG38", anotherList.get(0).getHg38Location());
            }
        }
        //把结果符合json格式
        Map<String, Object> cleanResult = this.getJsonResult(result);
        return cleanResult;
    }

    @Override
    public Map<String, Object> getGeneByEntrezId(String EntrezId) {
        // TODO Auto-generated method stub
        Map<String, String> result = geneDao.getGeneByEntrezId(EntrezId);
        if (StringUtils.isNotBlank(result.get("entrez_id"))) {
            EntrezAnotherExample entrezAnotherExample = new EntrezAnotherExample();
            entrezAnotherExample.createCriteria().andEntrezIdEqualTo(Integer.valueOf(result.get("entrez_id")));
            List<EntrezAnother> anotherList = entrezAnotherMapper.selectByExample(entrezAnotherExample);
            if (!anotherList.isEmpty() && anotherList.get(0).getHg38Location() != null) {
                result.put("HG38", anotherList.get(0).getHg38Location());
            }
        }
        //把结果符合json格式
        Map<String, Object> cleanResult = this.getJsonResult(result);
        return cleanResult;
    }

    /**
     * 先查询是否有别名/没有的话直接查询,有的话按照别名查询
     *
     * @param geneSymbol
     * @return
     */
    @Override
    public Map<String, Object> getGeneByGeneSymbol(String geneSymbol) {
        EntrezAnotherExample entrezAnotherExample = new EntrezAnotherExample();
        entrezAnotherExample.createCriteria().andAnotherNameLike("%" + geneSymbol + "%");
        String hg38 = "";
        List<EntrezAnother> entrezAnotherList = entrezAnotherMapper.selectByExample(entrezAnotherExample);
        if (!entrezAnotherList.isEmpty()) {
            for (EntrezAnother entrezAnother : entrezAnotherList) {
                String anotherName = entrezAnother.getAnotherName();
                if (anotherName.contains(",")) {
                    String[] split = anotherName.split(",");
                    List<String> another = Arrays.asList(split);
                    for (String name : another) {
                        if (name.equals(geneSymbol)) {
                            log.info("查询基因ID别名{}",name);
                            geneSymbol = entrezAnother.getEntrezName();
                            log.info("查询基因ID别名{}",geneSymbol);
                            hg38 = entrezAnother.getHg38Location();
                            Map<String, String> result = geneDao.getGeneByGeneSymbol(geneSymbol);
                            //把结果符合json格式
                            Map<String, Object> cleanResult = this.getJsonResult(result);
                            cleanResult.put("HG38", hg38);
                            return cleanResult;
                        }
                    }
                }else {
                    if (anotherName.equals(geneSymbol)) {
                        Map<String, String> result = geneDao.getGeneByGeneSymbol(geneSymbol);
                        //把结果符合json格式
                        Map<String, Object> cleanResult = this.getJsonResult(result);
                        cleanResult.put("HG38", hg38);
                        return cleanResult;
                    }
                }
            }
        } else {
            Map<String, String> result = geneDao.getGeneByGeneSymbol(geneSymbol);
            //把结果符合json格式
            Map<String, Object> cleanResult = this.getJsonResult(result);
            cleanResult.put("HG38", hg38);
            return cleanResult;
        }
//        daoru();
        return null;
    }

    private void daoru() {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(paths2));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                EntrezAnother txt = new EntrezAnother();
                String[] arr = line.split("\t");
                log.info("数组长度{}", arr.length);
//                log.info("第一个数字:{}===第2个数字:{}===第三个数字:{}====第4个数字:{}",arr[0],arr[1],arr[2],arr[3]);
                if (arr.length == 1) {
                    txt.setEntrezId(Integer.valueOf(arr[0]));
                    entrezAnotherMapper.insert(txt);
                }
                if (arr.length == 2) {
                    txt.setEntrezId(Integer.valueOf(arr[0]));
                    txt.setEntrezName(arr[1]);
                    entrezAnotherMapper.insert(txt);
                }
                if (arr.length == 3) {
                    txt.setEntrezId(Integer.valueOf(arr[0]));
                    txt.setEntrezName(arr[1]);
                    txt.setAnotherName(arr[2]);
                    entrezAnotherMapper.insert(txt);
                }
                if (arr.length == 4) {
                    txt.setEntrezId(Integer.valueOf(arr[0]));
                    txt.setEntrezName(arr[1]);
                    txt.setAnotherName(arr[2]);
                    txt.setHg38Location(arr[3]);
                    entrezAnotherMapper.insert(txt);
                }
                count++;

            }
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
