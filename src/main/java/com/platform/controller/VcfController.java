package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.UserInfo;
import com.platform.model.VcfFile;
import com.platform.service.VcfService;
import com.platform.util.PdfUtilTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * VCF管理
 */
@Api(tags = "VCF管理")
@RestController
@RequestMapping("mtApi/")
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
//    @ApiOperation("导出解析报告pdf")
//    @GetMapping(value = "/vcf/export", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> exportVcf(@ApiParam("患者ID") @RequestParam(value = "patientId") String patientId) {
//        try {
//            ResponseEntity<?> responseEntity = vcfService.exportPdf(patientId);
//            return responseEntity;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
//                headers, HttpStatus.NOT_FOUND);
//    }
    @ApiOperation("导出解析报告pdf")
    @GetMapping(value = "/vcf/export", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void exportVcf(@ApiParam("患者ID") @RequestParam(value = "patientId") String patientId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        vcfService.download(patientId,response);
//        ByteArrayOutputStream baos = null;
//        OutputStream out = null;
//        try {
//            // 模板中的数据，实际运用从数据库中查询
//            Map<String,Object> data = new HashMap<>();
//            data.put("name", "李逍遥");
//            baos = PdfUtilTest.createPDF(data, "pdfPage.ftl");;
//            // 设置响应消息头，告诉浏览器当前响应是一个下载文件
//            response.setContentType( "application/x-msdownload");
//            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
//            String fileName = URLEncoder.encode("获奖证书.pdf", "UTF-8");
//            response.setHeader( "Content-Disposition", "attachment;filename=" + fileName);
//            out = response.getOutputStream();
//            baos.writeTo(out);
//            baos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception("导出失败：" + e.getMessage());
//        } finally{
//            if(baos != null){
//                baos.close();
//            }
//            if(out != null){
//                out.close();
//            }
//        }
    }
}
