package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.dao.VcfFileMapper;
import com.platform.model.VcfFile;
import com.platform.service.PdfService;
import com.platform.util.PdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService {
    @Autowired
    private VcfFileMapper vcfFileMapper;
    @Autowired
    PdfUtil pdfUtil;
    @Override
    public void exportPdf(String id, HttpServletRequest request, HttpServletResponse response) {
        log.info("================= 通过vcf id查询内容{}",id);
        //查询vcf解析详情
        VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(Integer.valueOf(id));
        String jsonResult = vcfFile.getJsonResult();
        //转化成json对象
        log.info("================= 将结果转成json====================");
        JSONObject json = (JSONObject) JSON.parse(jsonResult);
        //响应中写入pdf输出流
        try {
            log.info("================= 响应中写入pdf输出流====================");
            //清除缓存
            response.reset();
            // 指定下载的文件名
            response.setHeader("Content-Disposition",
                    "attachment;filename=vc_report_" + new Date() + ".pdf");
            OutputStream out = response.getOutputStream();
            pdfUtil.createPDF(json, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
