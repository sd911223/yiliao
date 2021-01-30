package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.DiseaseOmimMapper;
import com.platform.dao.LiteratureMapper;
import com.platform.dao.LiteratureMaterialMapper;
import com.platform.dao.VariationMessageMapper;
import com.platform.model.*;
import com.platform.service.VariationService;
import com.platform.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shitou
 */
@Service
@Slf4j
public class VariationServiceImpl implements VariationService {
    @Autowired
    VariationMessageMapper variationMessageMapper;
    @Autowired
    DiseaseOmimMapper diseaseOmimMapper;
    @Autowired
    LiteratureMaterialMapper literatureMaterialMapper;
    @Autowired
    LiteratureMapper literatureMapper;
    @Autowired
    RedisUtil redisUtil;
    public static String paths = "/home/ec2-user/grakn_data/variants/vcf_annotation4.txt";

    public static String paths2 = "C:\\Users\\shidun\\Desktop\\医疗\\disease_id_disease_name.txt";
    public static String paths3 = "C:\\Users\\shidun\\Desktop\\ref_new1.txt";

    /**
     * 通过变异ID查询
     *
     * @param rsId
     * @return
     */
    @Override
    public RestResponse variationService(String rsId) {
        if (redisUtil.get("ByRsVariationId:" + rsId) == null) {
            VariationMessageExample messageExample = new VariationMessageExample();
            messageExample.createCriteria().andRsEqualTo(rsId);
            List<VariationMessage> variationMessages = variationMessageMapper.selectByExample(messageExample);
            if (!variationMessages.isEmpty()) {
                for (VariationMessage variationMessage : variationMessages) {
                    //处理疾病
                    variationMessage.setVariantPhenotype11(replaceString(variationMessage.getVariantPhenotype()));
                    //疾病名称
                    variationMessage.setDiseaseOmimList(getMapList(replaceString(variationMessage.getVariantPhenotype())));
                    //处理文献
                    variationMessage.setVariantPmid11(replaceString(variationMessage.getVariantPmid()));
                    //处理遗传方式
                    variationMessage.setVariantSource11(replaceString(variationMessage.getVariantSource()));
                }
                redisUtil.set("ByRsVariationId:" + rsId, JSON.toJSONString(variationMessages));
                return ResultUtil.success(variationMessages);
            }
        } else {
            Object o = redisUtil.get("ByRsVariationId:" + rsId);
            List<VariationMessage> variationMessages = JSON.parseArray(o.toString(), VariationMessage.class);
            return ResultUtil.success(variationMessages);
        }
        return null;
    }

    private List<DiseaseOmim> getMapList(String[] omidS) {
        log.info("查询疾病名称:{}", omidS);
        List<DiseaseOmim> list = new ArrayList<>();
        if (null == omidS) {
            return list;
        }
        List<String> stringList = Arrays.asList(omidS);

        stringList.forEach(e -> {
            DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
            diseaseOmimExample.createCriteria().andOmimIdEqualTo(Integer.valueOf(e));
            List<DiseaseOmim> omimList = diseaseOmimMapper.selectByExample(diseaseOmimExample);
            list.add(omimList.get(0));
        });
        return list;
    }

    private String[] replaceString(String replaceString) {
        if ("".equals(replaceString)) {
            return new String[]{};
        }
        if (replaceString.contains(",")) {
            if (replaceString.contains("[")) {
                String replace = replaceString.replace("[", "");
                if (replace.contains("]")) {
                    String replace1 = replace.replace("]", "");
                    String replace2 = replace1.replace("\"", "");
                    String replace3 = replace2.replace(" ", "");
                    String[] split = replace3.split(",");
                    return split;
                }
            } else {
                return new String[]{replaceString};
            }
        }

        return null;
    }


    @Override
    public RestResponse diseaseName(Integer rsId) {
        if (redisUtil.get("diseaseNameByRs:" + rsId) == null) {
            DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
            diseaseOmimExample.createCriteria().andOmimIdEqualTo(rsId);
            List<DiseaseOmim> omimList = diseaseOmimMapper.selectByExample(diseaseOmimExample);
            redisUtil.set("diseaseNameByRs:" + rsId, JSON.toJSONString(omimList));
            return ResultUtil.success(omimList);
        } else {
            Object o = redisUtil.get("diseaseNameByRs:" + rsId);
            List<DiseaseOmim> omimList = JSON.parseArray(o.toString(), DiseaseOmim.class);
            return ResultUtil.success(omimList);
        }
    }

    @Override
    public void DeImport() {
//        daoru1();
//        daoru();
        daoru3();
    }

    private void daoru3() {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(paths3));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                Literature txt = new Literature();
                String[] arr = line.split("\t");
                if (arr.length > 0) {
                    txt.setLiteratureId(Integer.valueOf(arr[0]));
                    txt.setAuthor(arr[1]);
                    txt.setTitle(arr[2]);
                    txt.setPeriodicalName(arr[3]);
                    txt.setPeriodicalNumber(arr[4]);
                    txt.setPublishingTime(arr[5]);
                    txt.setSummary(arr[6]);
                    literatureMapper.insert(txt);
                }
                count++;

            }
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void daoru() {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(paths));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                VariationMessage txt = new VariationMessage();
                String[] arr = line.split("\t");
                if (arr.length > 5) {
                    txt.setRs(arr[0]);
                    txt.setChr(arr[1]);
                    txt.setStart(arr[2]);
                    txt.setEnd(arr[3]);
                    txt.setRef(arr[4]);
                    txt.setAlt(arr[5]);
                    txt.setGene(arr[6]);
                    txt.setLabel(arr[7]);
                    txt.setExonicfunc(arr[8]);
                    txt.setAachange(arr[9]);
                    txt.setMafGnomad(arr[10]);
                    txt.setVariantPhenotype(arr[11]);
                    txt.setVariantSource(arr[12]);
                    txt.setVariantPmid(arr[13]);
                    txt.setVariantInheritance(arr[14]);
                    txt.setGenePhenotype(arr[15]);
                    txt.setGeneSource(arr[16]);
                    txt.setGenePmid(arr[17]);
                    txt.setGeneInheritance(arr[18]);
                    txt.setnChange(arr[19]);
                    variationMessageMapper.insert(txt);
                }
                count++;

            }
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void daoru1() {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(paths2));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                DiseaseOmim txt = new DiseaseOmim();
                String[] arr = line.split("\t");
                if (arr.length > 1) {
                    txt.setOmimId(Integer.valueOf(arr[0]));
                    txt.setDiseaseName(arr[1]);

                    diseaseOmimMapper.insert(txt);
                }
                count++;

            }
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
