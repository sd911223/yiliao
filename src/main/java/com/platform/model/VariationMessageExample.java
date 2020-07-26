package com.platform.model;

import java.util.ArrayList;
import java.util.List;

public class VariationMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VariationMessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRsIsNull() {
            addCriterion("RS is null");
            return (Criteria) this;
        }

        public Criteria andRsIsNotNull() {
            addCriterion("RS is not null");
            return (Criteria) this;
        }

        public Criteria andRsEqualTo(String value) {
            addCriterion("RS =", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsNotEqualTo(String value) {
            addCriterion("RS <>", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsGreaterThan(String value) {
            addCriterion("RS >", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsGreaterThanOrEqualTo(String value) {
            addCriterion("RS >=", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsLessThan(String value) {
            addCriterion("RS <", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsLessThanOrEqualTo(String value) {
            addCriterion("RS <=", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsLike(String value) {
            addCriterion("RS like", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsNotLike(String value) {
            addCriterion("RS not like", value, "rs");
            return (Criteria) this;
        }

        public Criteria andRsIn(List<String> values) {
            addCriterion("RS in", values, "rs");
            return (Criteria) this;
        }

        public Criteria andRsNotIn(List<String> values) {
            addCriterion("RS not in", values, "rs");
            return (Criteria) this;
        }

        public Criteria andRsBetween(String value1, String value2) {
            addCriterion("RS between", value1, value2, "rs");
            return (Criteria) this;
        }

        public Criteria andRsNotBetween(String value1, String value2) {
            addCriterion("RS not between", value1, value2, "rs");
            return (Criteria) this;
        }

        public Criteria andChrIsNull() {
            addCriterion("Chr is null");
            return (Criteria) this;
        }

        public Criteria andChrIsNotNull() {
            addCriterion("Chr is not null");
            return (Criteria) this;
        }

        public Criteria andChrEqualTo(String value) {
            addCriterion("Chr =", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrNotEqualTo(String value) {
            addCriterion("Chr <>", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrGreaterThan(String value) {
            addCriterion("Chr >", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrGreaterThanOrEqualTo(String value) {
            addCriterion("Chr >=", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrLessThan(String value) {
            addCriterion("Chr <", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrLessThanOrEqualTo(String value) {
            addCriterion("Chr <=", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrLike(String value) {
            addCriterion("Chr like", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrNotLike(String value) {
            addCriterion("Chr not like", value, "chr");
            return (Criteria) this;
        }

        public Criteria andChrIn(List<String> values) {
            addCriterion("Chr in", values, "chr");
            return (Criteria) this;
        }

        public Criteria andChrNotIn(List<String> values) {
            addCriterion("Chr not in", values, "chr");
            return (Criteria) this;
        }

        public Criteria andChrBetween(String value1, String value2) {
            addCriterion("Chr between", value1, value2, "chr");
            return (Criteria) this;
        }

        public Criteria andChrNotBetween(String value1, String value2) {
            addCriterion("Chr not between", value1, value2, "chr");
            return (Criteria) this;
        }

        public Criteria andStartIsNull() {
            addCriterion("Start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("Start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(String value) {
            addCriterion("Start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(String value) {
            addCriterion("Start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(String value) {
            addCriterion("Start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(String value) {
            addCriterion("Start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(String value) {
            addCriterion("Start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(String value) {
            addCriterion("Start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLike(String value) {
            addCriterion("Start like", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotLike(String value) {
            addCriterion("Start not like", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<String> values) {
            addCriterion("Start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<String> values) {
            addCriterion("Start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(String value1, String value2) {
            addCriterion("Start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(String value1, String value2) {
            addCriterion("Start not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andEndIsNull() {
            addCriterion("End is null");
            return (Criteria) this;
        }

        public Criteria andEndIsNotNull() {
            addCriterion("End is not null");
            return (Criteria) this;
        }

        public Criteria andEndEqualTo(String value) {
            addCriterion("End =", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotEqualTo(String value) {
            addCriterion("End <>", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThan(String value) {
            addCriterion("End >", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThanOrEqualTo(String value) {
            addCriterion("End >=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThan(String value) {
            addCriterion("End <", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThanOrEqualTo(String value) {
            addCriterion("End <=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLike(String value) {
            addCriterion("End like", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotLike(String value) {
            addCriterion("End not like", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndIn(List<String> values) {
            addCriterion("End in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotIn(List<String> values) {
            addCriterion("End not in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndBetween(String value1, String value2) {
            addCriterion("End between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotBetween(String value1, String value2) {
            addCriterion("End not between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andRefIsNull() {
            addCriterion("Ref is null");
            return (Criteria) this;
        }

        public Criteria andRefIsNotNull() {
            addCriterion("Ref is not null");
            return (Criteria) this;
        }

        public Criteria andRefEqualTo(String value) {
            addCriterion("Ref =", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotEqualTo(String value) {
            addCriterion("Ref <>", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefGreaterThan(String value) {
            addCriterion("Ref >", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefGreaterThanOrEqualTo(String value) {
            addCriterion("Ref >=", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefLessThan(String value) {
            addCriterion("Ref <", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefLessThanOrEqualTo(String value) {
            addCriterion("Ref <=", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefLike(String value) {
            addCriterion("Ref like", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotLike(String value) {
            addCriterion("Ref not like", value, "ref");
            return (Criteria) this;
        }

        public Criteria andRefIn(List<String> values) {
            addCriterion("Ref in", values, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotIn(List<String> values) {
            addCriterion("Ref not in", values, "ref");
            return (Criteria) this;
        }

        public Criteria andRefBetween(String value1, String value2) {
            addCriterion("Ref between", value1, value2, "ref");
            return (Criteria) this;
        }

        public Criteria andRefNotBetween(String value1, String value2) {
            addCriterion("Ref not between", value1, value2, "ref");
            return (Criteria) this;
        }

        public Criteria andAltIsNull() {
            addCriterion("Alt is null");
            return (Criteria) this;
        }

        public Criteria andAltIsNotNull() {
            addCriterion("Alt is not null");
            return (Criteria) this;
        }

        public Criteria andAltEqualTo(String value) {
            addCriterion("Alt =", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotEqualTo(String value) {
            addCriterion("Alt <>", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltGreaterThan(String value) {
            addCriterion("Alt >", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltGreaterThanOrEqualTo(String value) {
            addCriterion("Alt >=", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltLessThan(String value) {
            addCriterion("Alt <", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltLessThanOrEqualTo(String value) {
            addCriterion("Alt <=", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltLike(String value) {
            addCriterion("Alt like", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotLike(String value) {
            addCriterion("Alt not like", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltIn(List<String> values) {
            addCriterion("Alt in", values, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotIn(List<String> values) {
            addCriterion("Alt not in", values, "alt");
            return (Criteria) this;
        }

        public Criteria andAltBetween(String value1, String value2) {
            addCriterion("Alt between", value1, value2, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotBetween(String value1, String value2) {
            addCriterion("Alt not between", value1, value2, "alt");
            return (Criteria) this;
        }

        public Criteria andGeneIsNull() {
            addCriterion("Gene is null");
            return (Criteria) this;
        }

        public Criteria andGeneIsNotNull() {
            addCriterion("Gene is not null");
            return (Criteria) this;
        }

        public Criteria andGeneEqualTo(String value) {
            addCriterion("Gene =", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneNotEqualTo(String value) {
            addCriterion("Gene <>", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneGreaterThan(String value) {
            addCriterion("Gene >", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneGreaterThanOrEqualTo(String value) {
            addCriterion("Gene >=", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneLessThan(String value) {
            addCriterion("Gene <", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneLessThanOrEqualTo(String value) {
            addCriterion("Gene <=", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneLike(String value) {
            addCriterion("Gene like", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneNotLike(String value) {
            addCriterion("Gene not like", value, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneIn(List<String> values) {
            addCriterion("Gene in", values, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneNotIn(List<String> values) {
            addCriterion("Gene not in", values, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneBetween(String value1, String value2) {
            addCriterion("Gene between", value1, value2, "gene");
            return (Criteria) this;
        }

        public Criteria andGeneNotBetween(String value1, String value2) {
            addCriterion("Gene not between", value1, value2, "gene");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andExonicfuncIsNull() {
            addCriterion("ExonicFunc is null");
            return (Criteria) this;
        }

        public Criteria andExonicfuncIsNotNull() {
            addCriterion("ExonicFunc is not null");
            return (Criteria) this;
        }

        public Criteria andExonicfuncEqualTo(String value) {
            addCriterion("ExonicFunc =", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncNotEqualTo(String value) {
            addCriterion("ExonicFunc <>", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncGreaterThan(String value) {
            addCriterion("ExonicFunc >", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncGreaterThanOrEqualTo(String value) {
            addCriterion("ExonicFunc >=", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncLessThan(String value) {
            addCriterion("ExonicFunc <", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncLessThanOrEqualTo(String value) {
            addCriterion("ExonicFunc <=", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncLike(String value) {
            addCriterion("ExonicFunc like", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncNotLike(String value) {
            addCriterion("ExonicFunc not like", value, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncIn(List<String> values) {
            addCriterion("ExonicFunc in", values, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncNotIn(List<String> values) {
            addCriterion("ExonicFunc not in", values, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncBetween(String value1, String value2) {
            addCriterion("ExonicFunc between", value1, value2, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andExonicfuncNotBetween(String value1, String value2) {
            addCriterion("ExonicFunc not between", value1, value2, "exonicfunc");
            return (Criteria) this;
        }

        public Criteria andAachangeIsNull() {
            addCriterion("AAChange is null");
            return (Criteria) this;
        }

        public Criteria andAachangeIsNotNull() {
            addCriterion("AAChange is not null");
            return (Criteria) this;
        }

        public Criteria andAachangeEqualTo(String value) {
            addCriterion("AAChange =", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeNotEqualTo(String value) {
            addCriterion("AAChange <>", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeGreaterThan(String value) {
            addCriterion("AAChange >", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeGreaterThanOrEqualTo(String value) {
            addCriterion("AAChange >=", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeLessThan(String value) {
            addCriterion("AAChange <", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeLessThanOrEqualTo(String value) {
            addCriterion("AAChange <=", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeLike(String value) {
            addCriterion("AAChange like", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeNotLike(String value) {
            addCriterion("AAChange not like", value, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeIn(List<String> values) {
            addCriterion("AAChange in", values, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeNotIn(List<String> values) {
            addCriterion("AAChange not in", values, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeBetween(String value1, String value2) {
            addCriterion("AAChange between", value1, value2, "aachange");
            return (Criteria) this;
        }

        public Criteria andAachangeNotBetween(String value1, String value2) {
            addCriterion("AAChange not between", value1, value2, "aachange");
            return (Criteria) this;
        }

        public Criteria andMafGnomadIsNull() {
            addCriterion("maf_gnomAD is null");
            return (Criteria) this;
        }

        public Criteria andMafGnomadIsNotNull() {
            addCriterion("maf_gnomAD is not null");
            return (Criteria) this;
        }

        public Criteria andMafGnomadEqualTo(String value) {
            addCriterion("maf_gnomAD =", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadNotEqualTo(String value) {
            addCriterion("maf_gnomAD <>", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadGreaterThan(String value) {
            addCriterion("maf_gnomAD >", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadGreaterThanOrEqualTo(String value) {
            addCriterion("maf_gnomAD >=", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadLessThan(String value) {
            addCriterion("maf_gnomAD <", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadLessThanOrEqualTo(String value) {
            addCriterion("maf_gnomAD <=", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadLike(String value) {
            addCriterion("maf_gnomAD like", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadNotLike(String value) {
            addCriterion("maf_gnomAD not like", value, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadIn(List<String> values) {
            addCriterion("maf_gnomAD in", values, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadNotIn(List<String> values) {
            addCriterion("maf_gnomAD not in", values, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadBetween(String value1, String value2) {
            addCriterion("maf_gnomAD between", value1, value2, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andMafGnomadNotBetween(String value1, String value2) {
            addCriterion("maf_gnomAD not between", value1, value2, "mafGnomad");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeIsNull() {
            addCriterion("variant_phenotype is null");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeIsNotNull() {
            addCriterion("variant_phenotype is not null");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeEqualTo(String value) {
            addCriterion("variant_phenotype =", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeNotEqualTo(String value) {
            addCriterion("variant_phenotype <>", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeGreaterThan(String value) {
            addCriterion("variant_phenotype >", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeGreaterThanOrEqualTo(String value) {
            addCriterion("variant_phenotype >=", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeLessThan(String value) {
            addCriterion("variant_phenotype <", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeLessThanOrEqualTo(String value) {
            addCriterion("variant_phenotype <=", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeLike(String value) {
            addCriterion("variant_phenotype like", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeNotLike(String value) {
            addCriterion("variant_phenotype not like", value, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeIn(List<String> values) {
            addCriterion("variant_phenotype in", values, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeNotIn(List<String> values) {
            addCriterion("variant_phenotype not in", values, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeBetween(String value1, String value2) {
            addCriterion("variant_phenotype between", value1, value2, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantPhenotypeNotBetween(String value1, String value2) {
            addCriterion("variant_phenotype not between", value1, value2, "variantPhenotype");
            return (Criteria) this;
        }

        public Criteria andVariantSourceIsNull() {
            addCriterion("variant_source is null");
            return (Criteria) this;
        }

        public Criteria andVariantSourceIsNotNull() {
            addCriterion("variant_source is not null");
            return (Criteria) this;
        }

        public Criteria andVariantSourceEqualTo(String value) {
            addCriterion("variant_source =", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceNotEqualTo(String value) {
            addCriterion("variant_source <>", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceGreaterThan(String value) {
            addCriterion("variant_source >", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceGreaterThanOrEqualTo(String value) {
            addCriterion("variant_source >=", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceLessThan(String value) {
            addCriterion("variant_source <", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceLessThanOrEqualTo(String value) {
            addCriterion("variant_source <=", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceLike(String value) {
            addCriterion("variant_source like", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceNotLike(String value) {
            addCriterion("variant_source not like", value, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceIn(List<String> values) {
            addCriterion("variant_source in", values, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceNotIn(List<String> values) {
            addCriterion("variant_source not in", values, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceBetween(String value1, String value2) {
            addCriterion("variant_source between", value1, value2, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantSourceNotBetween(String value1, String value2) {
            addCriterion("variant_source not between", value1, value2, "variantSource");
            return (Criteria) this;
        }

        public Criteria andVariantPmidIsNull() {
            addCriterion("variant_PMID is null");
            return (Criteria) this;
        }

        public Criteria andVariantPmidIsNotNull() {
            addCriterion("variant_PMID is not null");
            return (Criteria) this;
        }

        public Criteria andVariantPmidEqualTo(String value) {
            addCriterion("variant_PMID =", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidNotEqualTo(String value) {
            addCriterion("variant_PMID <>", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidGreaterThan(String value) {
            addCriterion("variant_PMID >", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidGreaterThanOrEqualTo(String value) {
            addCriterion("variant_PMID >=", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidLessThan(String value) {
            addCriterion("variant_PMID <", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidLessThanOrEqualTo(String value) {
            addCriterion("variant_PMID <=", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidLike(String value) {
            addCriterion("variant_PMID like", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidNotLike(String value) {
            addCriterion("variant_PMID not like", value, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidIn(List<String> values) {
            addCriterion("variant_PMID in", values, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidNotIn(List<String> values) {
            addCriterion("variant_PMID not in", values, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidBetween(String value1, String value2) {
            addCriterion("variant_PMID between", value1, value2, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantPmidNotBetween(String value1, String value2) {
            addCriterion("variant_PMID not between", value1, value2, "variantPmid");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceIsNull() {
            addCriterion("variant_Inheritance is null");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceIsNotNull() {
            addCriterion("variant_Inheritance is not null");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceEqualTo(String value) {
            addCriterion("variant_Inheritance =", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceNotEqualTo(String value) {
            addCriterion("variant_Inheritance <>", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceGreaterThan(String value) {
            addCriterion("variant_Inheritance >", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceGreaterThanOrEqualTo(String value) {
            addCriterion("variant_Inheritance >=", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceLessThan(String value) {
            addCriterion("variant_Inheritance <", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceLessThanOrEqualTo(String value) {
            addCriterion("variant_Inheritance <=", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceLike(String value) {
            addCriterion("variant_Inheritance like", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceNotLike(String value) {
            addCriterion("variant_Inheritance not like", value, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceIn(List<String> values) {
            addCriterion("variant_Inheritance in", values, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceNotIn(List<String> values) {
            addCriterion("variant_Inheritance not in", values, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceBetween(String value1, String value2) {
            addCriterion("variant_Inheritance between", value1, value2, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andVariantInheritanceNotBetween(String value1, String value2) {
            addCriterion("variant_Inheritance not between", value1, value2, "variantInheritance");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeIsNull() {
            addCriterion("gene_phenotype is null");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeIsNotNull() {
            addCriterion("gene_phenotype is not null");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeEqualTo(String value) {
            addCriterion("gene_phenotype =", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeNotEqualTo(String value) {
            addCriterion("gene_phenotype <>", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeGreaterThan(String value) {
            addCriterion("gene_phenotype >", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeGreaterThanOrEqualTo(String value) {
            addCriterion("gene_phenotype >=", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeLessThan(String value) {
            addCriterion("gene_phenotype <", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeLessThanOrEqualTo(String value) {
            addCriterion("gene_phenotype <=", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeLike(String value) {
            addCriterion("gene_phenotype like", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeNotLike(String value) {
            addCriterion("gene_phenotype not like", value, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeIn(List<String> values) {
            addCriterion("gene_phenotype in", values, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeNotIn(List<String> values) {
            addCriterion("gene_phenotype not in", values, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeBetween(String value1, String value2) {
            addCriterion("gene_phenotype between", value1, value2, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGenePhenotypeNotBetween(String value1, String value2) {
            addCriterion("gene_phenotype not between", value1, value2, "genePhenotype");
            return (Criteria) this;
        }

        public Criteria andGeneSourceIsNull() {
            addCriterion("gene_source is null");
            return (Criteria) this;
        }

        public Criteria andGeneSourceIsNotNull() {
            addCriterion("gene_source is not null");
            return (Criteria) this;
        }

        public Criteria andGeneSourceEqualTo(String value) {
            addCriterion("gene_source =", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceNotEqualTo(String value) {
            addCriterion("gene_source <>", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceGreaterThan(String value) {
            addCriterion("gene_source >", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceGreaterThanOrEqualTo(String value) {
            addCriterion("gene_source >=", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceLessThan(String value) {
            addCriterion("gene_source <", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceLessThanOrEqualTo(String value) {
            addCriterion("gene_source <=", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceLike(String value) {
            addCriterion("gene_source like", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceNotLike(String value) {
            addCriterion("gene_source not like", value, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceIn(List<String> values) {
            addCriterion("gene_source in", values, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceNotIn(List<String> values) {
            addCriterion("gene_source not in", values, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceBetween(String value1, String value2) {
            addCriterion("gene_source between", value1, value2, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGeneSourceNotBetween(String value1, String value2) {
            addCriterion("gene_source not between", value1, value2, "geneSource");
            return (Criteria) this;
        }

        public Criteria andGenePmidIsNull() {
            addCriterion("gene_PMID is null");
            return (Criteria) this;
        }

        public Criteria andGenePmidIsNotNull() {
            addCriterion("gene_PMID is not null");
            return (Criteria) this;
        }

        public Criteria andGenePmidEqualTo(String value) {
            addCriterion("gene_PMID =", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidNotEqualTo(String value) {
            addCriterion("gene_PMID <>", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidGreaterThan(String value) {
            addCriterion("gene_PMID >", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidGreaterThanOrEqualTo(String value) {
            addCriterion("gene_PMID >=", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidLessThan(String value) {
            addCriterion("gene_PMID <", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidLessThanOrEqualTo(String value) {
            addCriterion("gene_PMID <=", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidLike(String value) {
            addCriterion("gene_PMID like", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidNotLike(String value) {
            addCriterion("gene_PMID not like", value, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidIn(List<String> values) {
            addCriterion("gene_PMID in", values, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidNotIn(List<String> values) {
            addCriterion("gene_PMID not in", values, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidBetween(String value1, String value2) {
            addCriterion("gene_PMID between", value1, value2, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGenePmidNotBetween(String value1, String value2) {
            addCriterion("gene_PMID not between", value1, value2, "genePmid");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceIsNull() {
            addCriterion("gene_Inheritance is null");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceIsNotNull() {
            addCriterion("gene_Inheritance is not null");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceEqualTo(String value) {
            addCriterion("gene_Inheritance =", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceNotEqualTo(String value) {
            addCriterion("gene_Inheritance <>", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceGreaterThan(String value) {
            addCriterion("gene_Inheritance >", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceGreaterThanOrEqualTo(String value) {
            addCriterion("gene_Inheritance >=", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceLessThan(String value) {
            addCriterion("gene_Inheritance <", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceLessThanOrEqualTo(String value) {
            addCriterion("gene_Inheritance <=", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceLike(String value) {
            addCriterion("gene_Inheritance like", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceNotLike(String value) {
            addCriterion("gene_Inheritance not like", value, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceIn(List<String> values) {
            addCriterion("gene_Inheritance in", values, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceNotIn(List<String> values) {
            addCriterion("gene_Inheritance not in", values, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceBetween(String value1, String value2) {
            addCriterion("gene_Inheritance between", value1, value2, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andGeneInheritanceNotBetween(String value1, String value2) {
            addCriterion("gene_Inheritance not between", value1, value2, "geneInheritance");
            return (Criteria) this;
        }

        public Criteria andNChangeIsNull() {
            addCriterion("N_change is null");
            return (Criteria) this;
        }

        public Criteria andNChangeIsNotNull() {
            addCriterion("N_change is not null");
            return (Criteria) this;
        }

        public Criteria andNChangeEqualTo(String value) {
            addCriterion("N_change =", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeNotEqualTo(String value) {
            addCriterion("N_change <>", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeGreaterThan(String value) {
            addCriterion("N_change >", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeGreaterThanOrEqualTo(String value) {
            addCriterion("N_change >=", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeLessThan(String value) {
            addCriterion("N_change <", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeLessThanOrEqualTo(String value) {
            addCriterion("N_change <=", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeLike(String value) {
            addCriterion("N_change like", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeNotLike(String value) {
            addCriterion("N_change not like", value, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeIn(List<String> values) {
            addCriterion("N_change in", values, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeNotIn(List<String> values) {
            addCriterion("N_change not in", values, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeBetween(String value1, String value2) {
            addCriterion("N_change between", value1, value2, "nChange");
            return (Criteria) this;
        }

        public Criteria andNChangeNotBetween(String value1, String value2) {
            addCriterion("N_change not between", value1, value2, "nChange");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}