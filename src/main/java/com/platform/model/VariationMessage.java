package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/26
 */
public class VariationMessage {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 变异ID
     */
    private String rs;

    /**
     * 
     */
    private String chr;

    /**
     * 
     */
    private String start;

    /**
     * 
     */
    private String end;

    /**
     * 
     */
    private String ref;

    /**
     * 
     */
    private String alt;

    /**
     * 
     */
    private String gene;

    /**
     * 
     */
    private String label;

    /**
     * 
     */
    private String exonicfunc;

    /**
     * 
     */
    private String aachange;

    /**
     * 
     */
    private String mafGnomad;

    /**
     * 
     */
    private String variantPhenotype;
    private String[] variantPhenotype11;



    /**
     * 
     */
    private String variantSource;
    private String[] variantSource11;

    /**
     * 
     */
    private String variantPmid;
    private String[] variantPmid11;

    public String[] getVariantPhenotype11() {
        return variantPhenotype11;
    }

    public String[] getVariantSource11() {
        return variantSource11;
    }

    public String[] getVariantPmid11() {
        return variantPmid11;
    }

    public void setVariantPhenotype11(String[] variantPhenotype11) {
        this.variantPhenotype11 = variantPhenotype11;
    }

    public void setVariantSource11(String[] variantSource11) {
        this.variantSource11 = variantSource11;
    }

    public void setVariantPmid11(String[] variantPmid11) {
        this.variantPmid11 = variantPmid11;
    }

    /**
     * 
     */
    private String variantInheritance;

    /**
     * 
     */
    private String genePhenotype;

    /**
     * 
     */
    private String geneSource;

    /**
     * 
     */
    private String genePmid;

    /**
     * 
     */
    private String geneInheritance;

    /**
     * 
     */
    private String nChange;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExonicfunc() {
        return exonicfunc;
    }

    public void setExonicfunc(String exonicfunc) {
        this.exonicfunc = exonicfunc;
    }

    public String getAachange() {
        return aachange;
    }

    public void setAachange(String aachange) {
        this.aachange = aachange;
    }

    public String getMafGnomad() {
        return mafGnomad;
    }

    public void setMafGnomad(String mafGnomad) {
        this.mafGnomad = mafGnomad;
    }

    public String getVariantPhenotype() {
        return variantPhenotype;
    }

    public void setVariantPhenotype(String variantPhenotype) {
        this.variantPhenotype = variantPhenotype;
    }

    public String getVariantSource() {
        return variantSource;
    }

    public void setVariantSource(String variantSource) {
        this.variantSource = variantSource;
    }

    public String getVariantPmid() {
        return variantPmid;
    }

    public void setVariantPmid(String variantPmid) {
        this.variantPmid = variantPmid;
    }

    public String getVariantInheritance() {
        return variantInheritance;
    }

    public void setVariantInheritance(String variantInheritance) {
        this.variantInheritance = variantInheritance;
    }

    public String getGenePhenotype() {
        return genePhenotype;
    }

    public void setGenePhenotype(String genePhenotype) {
        this.genePhenotype = genePhenotype;
    }

    public String getGeneSource() {
        return geneSource;
    }

    public void setGeneSource(String geneSource) {
        this.geneSource = geneSource;
    }

    public String getGenePmid() {
        return genePmid;
    }

    public void setGenePmid(String genePmid) {
        this.genePmid = genePmid;
    }

    public String getGeneInheritance() {
        return geneInheritance;
    }

    public void setGeneInheritance(String geneInheritance) {
        this.geneInheritance = geneInheritance;
    }

    public String getnChange() {
        return nChange;
    }

    public void setnChange(String nChange) {
        this.nChange = nChange;
    }
}