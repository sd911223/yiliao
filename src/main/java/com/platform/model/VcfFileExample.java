package com.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VcfFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VcfFileExample() {
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

        public Criteria andJobNameIsNull() {
            addCriterion("job_name is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("job_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("job_name =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("job_name <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("job_name >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_name >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("job_name <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("job_name <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("job_name like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("job_name not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("job_name in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("job_name not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("job_name between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("job_name not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andGeneTypeIsNull() {
            addCriterion("gene_type is null");
            return (Criteria) this;
        }

        public Criteria andGeneTypeIsNotNull() {
            addCriterion("gene_type is not null");
            return (Criteria) this;
        }

        public Criteria andGeneTypeEqualTo(String value) {
            addCriterion("gene_type =", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeNotEqualTo(String value) {
            addCriterion("gene_type <>", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeGreaterThan(String value) {
            addCriterion("gene_type >", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeGreaterThanOrEqualTo(String value) {
            addCriterion("gene_type >=", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeLessThan(String value) {
            addCriterion("gene_type <", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeLessThanOrEqualTo(String value) {
            addCriterion("gene_type <=", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeLike(String value) {
            addCriterion("gene_type like", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeNotLike(String value) {
            addCriterion("gene_type not like", value, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeIn(List<String> values) {
            addCriterion("gene_type in", values, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeNotIn(List<String> values) {
            addCriterion("gene_type not in", values, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeBetween(String value1, String value2) {
            addCriterion("gene_type between", value1, value2, "geneType");
            return (Criteria) this;
        }

        public Criteria andGeneTypeNotBetween(String value1, String value2) {
            addCriterion("gene_type not between", value1, value2, "geneType");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseIsNull() {
            addCriterion("attention_disease is null");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseIsNotNull() {
            addCriterion("attention_disease is not null");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseEqualTo(String value) {
            addCriterion("attention_disease =", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseNotEqualTo(String value) {
            addCriterion("attention_disease <>", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseGreaterThan(String value) {
            addCriterion("attention_disease >", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("attention_disease >=", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseLessThan(String value) {
            addCriterion("attention_disease <", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseLessThanOrEqualTo(String value) {
            addCriterion("attention_disease <=", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseLike(String value) {
            addCriterion("attention_disease like", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseNotLike(String value) {
            addCriterion("attention_disease not like", value, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseIn(List<String> values) {
            addCriterion("attention_disease in", values, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseNotIn(List<String> values) {
            addCriterion("attention_disease not in", values, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseBetween(String value1, String value2) {
            addCriterion("attention_disease between", value1, value2, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andAttentionDiseaseNotBetween(String value1, String value2) {
            addCriterion("attention_disease not between", value1, value2, "attentionDisease");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeIsNull() {
            addCriterion("symptom_type is null");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeIsNotNull() {
            addCriterion("symptom_type is not null");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeEqualTo(String value) {
            addCriterion("symptom_type =", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeNotEqualTo(String value) {
            addCriterion("symptom_type <>", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeGreaterThan(String value) {
            addCriterion("symptom_type >", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeGreaterThanOrEqualTo(String value) {
            addCriterion("symptom_type >=", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeLessThan(String value) {
            addCriterion("symptom_type <", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeLessThanOrEqualTo(String value) {
            addCriterion("symptom_type <=", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeLike(String value) {
            addCriterion("symptom_type like", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeNotLike(String value) {
            addCriterion("symptom_type not like", value, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeIn(List<String> values) {
            addCriterion("symptom_type in", values, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeNotIn(List<String> values) {
            addCriterion("symptom_type not in", values, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeBetween(String value1, String value2) {
            addCriterion("symptom_type between", value1, value2, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomTypeNotBetween(String value1, String value2) {
            addCriterion("symptom_type not between", value1, value2, "symptomType");
            return (Criteria) this;
        }

        public Criteria andSymptomIsNull() {
            addCriterion("symptom is null");
            return (Criteria) this;
        }

        public Criteria andSymptomIsNotNull() {
            addCriterion("symptom is not null");
            return (Criteria) this;
        }

        public Criteria andSymptomEqualTo(String value) {
            addCriterion("symptom =", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomNotEqualTo(String value) {
            addCriterion("symptom <>", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomGreaterThan(String value) {
            addCriterion("symptom >", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomGreaterThanOrEqualTo(String value) {
            addCriterion("symptom >=", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomLessThan(String value) {
            addCriterion("symptom <", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomLessThanOrEqualTo(String value) {
            addCriterion("symptom <=", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomLike(String value) {
            addCriterion("symptom like", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomNotLike(String value) {
            addCriterion("symptom not like", value, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomIn(List<String> values) {
            addCriterion("symptom in", values, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomNotIn(List<String> values) {
            addCriterion("symptom not in", values, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomBetween(String value1, String value2) {
            addCriterion("symptom between", value1, value2, "symptom");
            return (Criteria) this;
        }

        public Criteria andSymptomNotBetween(String value1, String value2) {
            addCriterion("symptom not between", value1, value2, "symptom");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andJsonResultIsNull() {
            addCriterion("json_result is null");
            return (Criteria) this;
        }

        public Criteria andJsonResultIsNotNull() {
            addCriterion("json_result is not null");
            return (Criteria) this;
        }

        public Criteria andJsonResultEqualTo(String value) {
            addCriterion("json_result =", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultNotEqualTo(String value) {
            addCriterion("json_result <>", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultGreaterThan(String value) {
            addCriterion("json_result >", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultGreaterThanOrEqualTo(String value) {
            addCriterion("json_result >=", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultLessThan(String value) {
            addCriterion("json_result <", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultLessThanOrEqualTo(String value) {
            addCriterion("json_result <=", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultLike(String value) {
            addCriterion("json_result like", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultNotLike(String value) {
            addCriterion("json_result not like", value, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultIn(List<String> values) {
            addCriterion("json_result in", values, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultNotIn(List<String> values) {
            addCriterion("json_result not in", values, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultBetween(String value1, String value2) {
            addCriterion("json_result between", value1, value2, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andJsonResultNotBetween(String value1, String value2) {
            addCriterion("json_result not between", value1, value2, "jsonResult");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIsNull() {
            addCriterion("is_effective is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIsNotNull() {
            addCriterion("is_effective is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveEqualTo(Integer value) {
            addCriterion("is_effective =", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotEqualTo(Integer value) {
            addCriterion("is_effective <>", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveGreaterThan(Integer value) {
            addCriterion("is_effective >", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_effective >=", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLessThan(Integer value) {
            addCriterion("is_effective <", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLessThanOrEqualTo(Integer value) {
            addCriterion("is_effective <=", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIn(List<Integer> values) {
            addCriterion("is_effective in", values, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotIn(List<Integer> values) {
            addCriterion("is_effective not in", values, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveBetween(Integer value1, Integer value2) {
            addCriterion("is_effective between", value1, value2, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotBetween(Integer value1, Integer value2) {
            addCriterion("is_effective not between", value1, value2, "isEffective");
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