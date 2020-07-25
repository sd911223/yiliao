package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.service.GeneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 基因控制器
 *
 * @author shitou
 */
@RestController
@RequestMapping(value = "mtApi/")
@Api(tags = "基因控制器")
public class GeneController {
    @Autowired
    GeneService geneService;

    /**
     * 通过基因OMIM id获取基因详细信息
     *
     * @param omimId
     * @return
     */
    @ApiOperation("通过基因OMIM id获取基因详细信息")
    @GetMapping("/gene/byOmId")
    public RestResponse geneOmimId(@ApiParam("基因ID") @RequestParam(value = "omim_id", required = true) String omimId) {

        Map<String, Object> result = geneService.getGeneByOmimId(omimId);
        return ResultUtil.success(result);

    }

    /**
     * 通过基因entrez id获取基因详细信息
     *
     * @param EntrezId
     * @return
     */
    @ApiOperation("通过基因entrez id获取基因详细信息")
    @GetMapping("/gene/byEntrezId")
    public RestResponse geneEntrezId(@RequestParam(value = "entrez_id", required = true) String EntrezId) {

        Map<String, Object> result = geneService.getGeneByEntrezId(EntrezId);
        return ResultUtil.success(result);

    }

    /**
     * 通过基因symbol获取基因详细信息
     *
     * @param geneSymbol
     * @return
     */
    @ApiOperation("通过基因symbol获取基因详细信息")
    public RestResponse geneGeneSymbol(@RequestParam(value = "gene_symbol", required = true) String geneSymbol) {

        Map<String, Object> result = geneService.getGeneByGeneSymbol(geneSymbol);
        return ResultUtil.success(result);

    }
}
