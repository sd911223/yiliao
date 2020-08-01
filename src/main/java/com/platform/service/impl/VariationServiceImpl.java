package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.DiseaseOmimMapper;
import com.platform.dao.VariationMessageMapper;
import com.platform.model.DiseaseOmim;
import com.platform.model.DiseaseOmimExample;
import com.platform.model.VariationMessage;
import com.platform.model.VariationMessageExample;
import com.platform.service.VariationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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
    public static String paths = "/home/ec2-user/grakn_data/variants/vcf_annotation4.txt";

    public static String paths2 = "C:\\Users\\shidun\\Desktop\\disease_id_disease_name.txt";

    /**
     * 通过变异ID查询
     *
     * @param rsId
     * @return
     */
    @Override
    public RestResponse variationService(String rsId) {
        VariationMessageExample messageExample = new VariationMessageExample();
        messageExample.createCriteria().andRsEqualTo(rsId);
        List<VariationMessage> variationMessages = variationMessageMapper.selectByExample(messageExample);
        return ResultUtil.success(variationMessages);
    }

    @Override
    public RestResponse diseaseName(Integer rsId) {
        DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
        diseaseOmimExample.createCriteria().andOmimIdEqualTo(rsId);
        List<DiseaseOmim> omimList = diseaseOmimMapper.selectByExample(diseaseOmimExample);
        return ResultUtil.success(omimList);
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