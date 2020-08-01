package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.UserInfo;
import com.platform.model.VcfFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * VCF管理
 */
public interface VcfService {
    /**
     * 统计VCF完成数量
     *
     * @param userInfo
     * @return
     */
    RestResponse<VcfCountResp> handleStatus(UserInfo userInfo);

    /**
     * 上传VCF
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
    VcfFile addVcf(MultipartFile vcfFile, String jobName, String geneType, String omimId, String patientId, String symptomType, String symptom);

    /**
     * vcf解析
     *
     * @param vcfFile
     * @param geneType
     * @param omimId
     * @param patientId
     * @param vcf
     */
    void vcfDecode(MultipartFile vcfFile, String geneType, String omimId, String patientId, VcfFile vcf);

    /**
     * 查看VCF
     *
     * @param vcfId
     * @return
     */
    RestResponse vcfDetail(Integer vcfId);

    /**
     * 删除VCF
     *
     * @param vcfId
     * @param patientId
     * @return
     */
    RestResponse vcfDelete(Integer vcfId, String patientId);

    /**
     * vcf导出
     *
     * @param id
     * @param response
     */
    void exportPdf(String id, HttpServletResponse response);
}
