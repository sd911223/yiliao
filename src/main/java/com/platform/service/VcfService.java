package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.UserInfo;
import com.platform.model.VcfFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * VCF管理
 */
public interface VcfService {
    /**
     * 统计VCF完成数量
     * @param userInfo
     * @return
     */
    RestResponse<VcfCountResp> handleStatus(UserInfo userInfo);

    VcfFile addVcf(MultipartFile vcfFile, String jobName, String geneType, String omimId, String patientId, String symptomType, String symptom);
}
