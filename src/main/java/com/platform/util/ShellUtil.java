package com.platform.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * shell脚本工具类
 *
 * @author shitou
 */
@Component
@Slf4j
public class ShellUtil {
    /**
     * 解析vcf文件
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public int analysisVcf(String vcfPath, String geneType, String jsonPath, String omimIdArray) {

        //脚本文件地址
        String command = "/usr/bin/bash /home/ec2-user/soft/vcf_report_code/report.sh " + vcfPath + " " + geneType + " " + jsonPath + " " + omimIdArray;
        //String[] commands = {"/usr/bin/bash", "-c", "~/soft/vcf_report_code/report.sh",vcfPath,geneType,jsonPath,omimIdArray};
        //String[] commands_permission = {"/usr/bin/chmod", "755","~/soft/vcf_report_code/report.sh"};
        log.info("shell执行命令：" + command);
        //赋予权限
        String permission = "/usr/bin/chmod 755 /home/ec2-user/soft/vcf_report_code/report.sh";
        int exitValue = -1;
        String s = null;
        try {
            //赋予权限
            log.info("赋予权限:" + permission);
            Process ps_permission = Runtime.getRuntime().exec(permission);
            ps_permission.waitFor();
            //执行脚本
            Process ps = Runtime.getRuntime().exec(command);
            ps.waitFor();
            exitValue = ps.exitValue();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                log.error(s);
            }
            exitValue = ps.waitFor();
            ps.destroy();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("shell执行结果：" + exitValue);
        return exitValue;

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //ShellUtil su = new ShellUtil();
        //su.analysisVcf();
    }
}
