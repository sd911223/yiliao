package com.platform.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.UserInfo;
import com.platform.model.VcfFile;
import com.platform.service.VcfService;
import com.platform.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * VCF管理
 */
@Api(tags = "VCF管理")
@RestController
@RequestMapping("mtApi/")
@Slf4j
public class VcfController {
    @Autowired
    VcfService vcfService;
    @Autowired
    TemplateEngine templateEngine;

    /**
     * 统计未处理,已处理，总数完成的vcf数量
     *
     * @param userInfo
     * @return 数量
     */
    @GetMapping("/vcf/processCount")
    @ApiOperation("vcf统计")
    public RestResponse<VcfCountResp> handleStatus(@LoginUser UserInfo userInfo) {
        return vcfService.handleStatus(userInfo);
    }

    /**
     * 上传vcf文件，并解析
     *
     * @param vcfFile
     * @param jobName
     * @param geneType
     * @param omimId
     * @param patientId
     * @param symptomType
     * @param symptom
     * @return
     */
    @ApiOperation("上传vcf文件，并解析")
    @PostMapping("/vcf/upload")
    public RestResponse vcfUpload(@ApiParam("VCF文件") @RequestParam(name = "vcf") MultipartFile vcfFile,
                                  @ApiParam("job名字") @RequestParam(name = "jobName") String jobName,
                                  @ApiParam("基因类型") @RequestParam(name = "geneType") String geneType,
                                  @ApiParam("疾病ID") @RequestParam(name = "omimId") String omimId,
                                  @ApiParam("患者ID") @RequestParam(name = "patientId") String patientId,
                                  @ApiParam("症状类型") @RequestParam(name = "symptomType") String symptomType,
                                  @ApiParam("症状") @RequestParam(name = "symptom") String symptom) {

        VcfFile vcf = vcfService.addVcf(vcfFile, jobName, geneType, omimId, patientId, symptomType, symptom);

        //调用vcf解析
        log.info("=====================调用vcf解析===================");
        vcfService.vcfDecode(vcfFile, geneType, omimId, patientId, vcf);
        //返回分析结果
        return ResultUtil.success();
    }

    /**
     * 查看VCF
     *
     * @param vcfId
     * @return
     */
    @ApiOperation("查看VCF")
    @GetMapping("/vcf/detail")
    public RestResponse vcfDetail(@ApiParam("vcfId") @RequestParam(name = "vcfId") Integer vcfId) {


        return vcfService.vcfDetail(vcfId);
    }

    /**
     * 删除VCF
     *
     * @param vcfId
     * @param patientId
     * @return
     */
    @ApiOperation("删除VCF")
    @GetMapping("/vcf/delete")
    public RestResponse vcfDelete(@ApiParam("vcfId") @RequestParam(name = "vcfId") Integer vcfId,
                                  @ApiParam("患者ID") @RequestParam(name = "patientId") String patientId) {


        return vcfService.vcfDelete(vcfId, patientId);
    }

    /**
     * 导出解析报告pdf
     *
     * @return
     */
    @ApiOperation("导出解析报告pdf")
    @PostMapping(value = "/vcf/export", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void exportVcf(@ApiParam("患者ID") @RequestParam(value = "patientId") String patientId,
                          HttpServletRequest request,
                          HttpServletResponse response, @LoginUser UserInfo userInfo) throws Exception {
        vcfService.download(patientId, response, userInfo);
    }

    public static void main(String[] args) {
        String json = "{\"高度关注\": {\"0\": {\"变异\": \"rs113288277\", \"染色体位置\": \"1:1044368\", \"Ref\": \"A\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0044843, \"基因\": \"AGRN\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.E728V\", \"相关疾病\": \"615120\", \"来源\": \"Clinvar/dbSNFP\", \"文献\": \"-\"}, \"1\": {\"变异\": \"rs242056\", \"染色体位置\": \"1:4712657\", \"Ref\": \"G\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": \"-\", \"基因\": \"AJAP1\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.G263R\", \"相关疾病\": [\"104300\", \"123400\", \"180300\", \"256700\"], \"来源\": [\"GWAS\", \"GWAS\", \"GWAS\", \"GWAS\"], \"文献\": [\"20061627\", \"22210626\", \"20453842\", \"21124317\"]}}, \"中度关注\": {\"0\": {\"变异\": \"rs3810982\", \"染色体位置\": \"1:6887657\", \"Ref\": \"C\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.275432, \"基因\": \"CAMTA1\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.L80F\", \"相关疾病\": \"614756\", \"来源\": \"OMIM/LOVD\", \"文献\": \"22693284,24738973\"}, \"1\": {\"变异\": \"rs228697\", \"染色体位置\": \"1:7827519\", \"Ref\": \"C\", \"Alt\": \"G\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0443523, \"基因\": \"PER3\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.P864A\", \"相关疾病\": [\"616882\", \"125853\", \"180300\"], \"来源\": [\"OMIM/LOVD\", \"GWAS\", \"GWAS\"], \"文献\": [\"26903630\", \"21647700\", \"20453842\"]}, \"2\": {\"变异\": \"rs17368528\", \"染色体位置\": \"1:9264154\", \"Ref\": \"C\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.05903, \"基因\": \"H6PD\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.P565L\", \"相关疾病\": \"604931\", \"来源\": \"OMIM/LOVD\", \"文献\": \"12858176,8370690,16091483,18628520,8923828,11150889,10522997,3990293,16817821,17062770\"}, \"3\": {\"变异\": \"rs2015352\", \"染色体位置\": \"1:16044572\", \"Ref\": \"G\", \"Alt\": \"T\", \"基因型\": \"1/1\", \"MAF(亚洲)\": \"-\", \"基因\": \"CLCNKB\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.R27L\", \"相关疾病\": [\"613090\", \"123400\", \"607364\"], \"来源\": [\"OMIM/LOVD\", \"GWAS\", \"LOVD\"], \"文献\": [\"9326936,15044642,8841184,18310267,22282380\", \"22210626\", \"None\"]}, \"4\": {\"变异\": \"rs4870\", \"染色体位置\": \"1:2556714\", \"Ref\": \"A\", \"Alt\": \"G\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.486547, \"基因\": \"TNFRSF14\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.K17R\", \"相关疾病\": \"180300\", \"来源\": \"GWAS\", \"文献\": \"20453842\"}, \"5\": {\"变异\": \"rs11585362\", \"染色体位置\": \"1:3512044\", \"Ref\": \"G\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": \"-\", \"基因\": \"MEGF6\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.A313V\", \"相关疾病\": \"176807\", \"来源\": \"GWAS\", \"文献\": \"23555315\"}, \"6\": {\"变异\": \"rs12407578\", \"染色体位置\": \"1:12277610\", \"Ref\": \"C\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": \"-\", \"基因\": \"VPS13D\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.S1341L\", \"相关疾病\": \"180300\", \"来源\": \"GWAS\", \"文献\": \"20453842\"}, \"7\": {\"变异\": \"rs61747667\", \"染色体位置\": \"1:13778554\", \"Ref\": \"C\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": \"-\", \"基因\": \"PRDM2\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.D52E\", \"相关疾病\": \"601665\", \"来源\": \"NLP\", \"文献\": \"32044406\"}, \"8\": {\"变异\": \"rs17350795\", \"染色体位置\": \"1:13779144\", \"Ref\": \"G\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0175607, \"基因\": \"PRDM2\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.S249N\", \"相关疾病\": \"601665\", \"来源\": \"NLP\", \"文献\": \"32044406\"}, \"9\": {\"变异\": \"rs116238585\", \"染色体位置\": \"1:13780640\", \"Ref\": \"C\", \"Alt\": \"G\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0175719, \"基因\": \"PRDM2\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.P748A\", \"相关疾病\": \"601665\", \"来源\": \"NLP\", \"文献\": \"32044406\"}}, \"其他\": {\"0\": {\"变异\": \"rs182217004\", \"染色体位置\": \"1:1072026\", \"Ref\": \"G\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0, \"基因\": \"RNF223\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.R181W\"}, \"1\": {\"变异\": \"rs116321663\", \"染色体位置\": \"1:1184997\", \"Ref\": \"T\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0, \"基因\": \"TTLL10\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.M357K\"}, \"2\": {\"变异\": \"rs1137003\", \"染色体位置\": \"1:1719348\", \"Ref\": \"T\", \"Alt\": \"C\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0, \"基因\": \"CDK11A\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.H112R\"}, \"3\": {\"变异\": \"rs56309807\", \"染色体位置\": \"1:3480498\", \"Ref\": \"G\", \"Alt\": \"A\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0, \"基因\": \"ARHGEF16\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.E681K\"}, \"4\": {\"变异\": \"rs2076063\", \"染色体位置\": \"1:12847517\", \"Ref\": \"A\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.0, \"基因\": \"HNRNPCL1\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.V258D\"}, \"5\": {\"变异\": \"rs76937665\", \"染色体位置\": \"1:13116056\", \"Ref\": \"A\", \"Alt\": \"C\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.000319693, \"基因\": \"HNRNPCL2,HNRNPCL3\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.F115L\"}, \"6\": {\"变异\": \"rs77505369\", \"染色体位置\": \"1:13116060\", \"Ref\": \"C\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.000319693, \"基因\": \"HNRNPCL2,HNRNPCL3\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.G114D\"}, \"7\": {\"变异\": \"rs77556803\", \"染色体位置\": \"1:13116061\", \"Ref\": \"C\", \"Alt\": \"T\", \"基因型\": \"0/1\", \"MAF(亚洲)\": 0.000319898, \"基因\": \"HNRNPCL2,HNRNPCL3\", \"致病分值\": \"1\", \"突变类型\": \"nonsynonymous\", \"蛋白变化\": \"p.G114S\"}}, \"高度关注_num\": 2, \"中度关注_num\": 10, \"其他_num\": 8}";
        JSONObject jsonObject = new JSONObject(json);
        Map map = JSON.parseObject(jsonObject.get("高度关注").toString(), Map.class);
        if (map.size() > 0) {
            for (int i = 0; i < map.size(); i++) {
                Map gaoDuMap = JSON.parseObject(new JSONObject(map.get(String.valueOf(i))).toString(), Map.class);
                System.out.println(gaoDuMap.get("文献"));
                String[] stingArray = new StringUtil().getStingArray(gaoDuMap.get("文献").toString());
                gaoDuMap.put("wenxian",stingArray);

                List<String> stringList = Arrays.asList(stingArray);
                stringList.forEach(e->{
                    System.out.println(e);
                });

            }
        }
        System.out.println();
//        List<String> list = JSON.parseArray(JSON.toJSONString(), String.class);
//        System.out.println(list.size());
    }
}
