package com.platform.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PDFExportConfig {
    /**
     * 宋体字体文件相对路径
     */
    @Value("${pdfExport.fontSimsun}")
    private String fontSimsun;

    /**
     * 员工绩效考核导出模板文件相对路径
     */
    @Value("${pdfExport.employeeKpiFtl}")
    private String employeeKpiFtl;
}
