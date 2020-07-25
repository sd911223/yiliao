package com.platform.service;

import java.util.Map;

/**
 * 基因持久层
 *
 * @author zhuchaojie
 */
public interface GeneDao {
    /**
     * 通过基因OMIM id获取基因详细信息
     *
     * @param omimId
     * @return
     */
    Map<String, String> getGeneByOmimId(String omimId);

    /**
     * 通过基因entrez id获取基因详细信息
     *
     * @param EntrezId
     * @return
     */
    Map<String, String> getGeneByEntrezId(String EntrezId);

    /**
     * 通过基因symbol获取基因详细信息
     *
     * @param geneSymbol
     * @return
     */
    Map<String, String> getGeneByGeneSymbol(String geneSymbol);
}
