package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.UserInfo;
import com.platform.model.VcfFile;
import com.platform.service.VcfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


/**
 * VCF管理
 */
@Api(tags = "VCF管理")
@RestController
@RequestMapping("mtApi/")
public class VcfController {
    @Autowired
    VcfService vcfService;

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
        //id
        UUID uuid = UUID.randomUUID();
        //调用vcf原始信息存入
//        Map<String, Object> result = vfs.insert(vcfFile, geneType, omimIdArray, patientId, doctorId, email, uuid);
        //调用vcf解析
//        vfs.vcfDecode(vcfFile, geneType, omimIdArray, email, uuid);
        //返回分析结果
        return ResultUtil.success();
    }
}
