package com.platform.service;

import java.util.Map;

/**
 * @author shitou
 */
public interface GeneService {
    /**
     * 通过基因OMIM id获取基因详细信息
     *
     * @param omimId
     * @return
     */
    Map<String, Object> getGeneByOmimId(String omimId);

    /**
     * 通过基因entrez id获取基因详细信息
     *
     * @param EntrezId
     * @return
     */
    Map<String, Object> getGeneByEntrezId(String EntrezId);

    /**
     * 通过基因symbol获取基因详细信息
     *
     * @param geneSymbol
     * @return
     */
    Map<String, Object> getGeneByGeneSymbol(String geneSymbol);
}
