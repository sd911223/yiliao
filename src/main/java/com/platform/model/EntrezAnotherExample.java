package com.platform.model;

import java.util.ArrayList;
import java.util.List;

public class EntrezAnotherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EntrezAnotherExample() {
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

        public Criteria andEntrezIdIsNull() {
            addCriterion("entrez_id is null");
            return (Criteria) this;
        }

        public Criteria andEntrezIdIsNotNull() {
            addCriterion("entrez_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntrezIdEqualTo(Integer value) {
            addCriterion("entrez_id =", value, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdNotEqualTo(Integer value) {
            addCriterion("entrez_id <>", value, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdGreaterThan(Integer value) {
            addCriterion("entrez_id >", value, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("entrez_id >=", value, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdLessThan(Integer value) {
            addCriterion("entrez_id <", value, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdLessThanOrEqualTo(Integer value) {
            addCriterion("entrez_id <=", value, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdIn(List<Integer> values) {
            addCriterion("entrez_id in", values, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdNotIn(List<Integer> values) {
            addCriterion("entrez_id not in", values, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdBetween(Integer value1, Integer value2) {
            addCriterion("entrez_id between", value1, value2, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezIdNotBetween(Integer value1, Integer value2) {
            addCriterion("entrez_id not between", value1, value2, "entrezId");
            return (Criteria) this;
        }

        public Criteria andEntrezNameIsNull() {
            addCriterion("entrez_name is null");
            return (Criteria) this;
        }

        public Criteria andEntrezNameIsNotNull() {
            addCriterion("entrez_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntrezNameEqualTo(String value) {
            addCriterion("entrez_name =", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameNotEqualTo(String value) {
            addCriterion("entrez_name <>", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameGreaterThan(String value) {
            addCriterion("entrez_name >", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameGreaterThanOrEqualTo(String value) {
            addCriterion("entrez_name >=", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameLessThan(String value) {
            addCriterion("entrez_name <", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameLessThanOrEqualTo(String value) {
            addCriterion("entrez_name <=", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameLike(String value) {
            addCriterion("entrez_name like", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameNotLike(String value) {
            addCriterion("entrez_name not like", value, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameIn(List<String> values) {
            addCriterion("entrez_name in", values, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameNotIn(List<String> values) {
            addCriterion("entrez_name not in", values, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameBetween(String value1, String value2) {
            addCriterion("entrez_name between", value1, value2, "entrezName");
            return (Criteria) this;
        }

        public Criteria andEntrezNameNotBetween(String value1, String value2) {
            addCriterion("entrez_name not between", value1, value2, "entrezName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameIsNull() {
            addCriterion("another_name is null");
            return (Criteria) this;
        }

        public Criteria andAnotherNameIsNotNull() {
            addCriterion("another_name is not null");
            return (Criteria) this;
        }

        public Criteria andAnotherNameEqualTo(String value) {
            addCriterion("another_name =", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameNotEqualTo(String value) {
            addCriterion("another_name <>", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameGreaterThan(String value) {
            addCriterion("another_name >", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameGreaterThanOrEqualTo(String value) {
            addCriterion("another_name >=", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameLessThan(String value) {
            addCriterion("another_name <", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameLessThanOrEqualTo(String value) {
            addCriterion("another_name <=", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameLike(String value) {
            addCriterion("another_name like", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameNotLike(String value) {
            addCriterion("another_name not like", value, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameIn(List<String> values) {
            addCriterion("another_name in", values, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameNotIn(List<String> values) {
            addCriterion("another_name not in", values, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameBetween(String value1, String value2) {
            addCriterion("another_name between", value1, value2, "anotherName");
            return (Criteria) this;
        }

        public Criteria andAnotherNameNotBetween(String value1, String value2) {
            addCriterion("another_name not between", value1, value2, "anotherName");
            return (Criteria) this;
        }

        public Criteria andHg38LocationIsNull() {
            addCriterion("hg38_location is null");
            return (Criteria) this;
        }

        public Criteria andHg38LocationIsNotNull() {
            addCriterion("hg38_location is not null");
            return (Criteria) this;
        }

        public Criteria andHg38LocationEqualTo(String value) {
            addCriterion("hg38_location =", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationNotEqualTo(String value) {
            addCriterion("hg38_location <>", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationGreaterThan(String value) {
            addCriterion("hg38_location >", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationGreaterThanOrEqualTo(String value) {
            addCriterion("hg38_location >=", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationLessThan(String value) {
            addCriterion("hg38_location <", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationLessThanOrEqualTo(String value) {
            addCriterion("hg38_location <=", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationLike(String value) {
            addCriterion("hg38_location like", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationNotLike(String value) {
            addCriterion("hg38_location not like", value, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationIn(List<String> values) {
            addCriterion("hg38_location in", values, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationNotIn(List<String> values) {
            addCriterion("hg38_location not in", values, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationBetween(String value1, String value2) {
            addCriterion("hg38_location between", value1, value2, "hg38Location");
            return (Criteria) this;
        }

        public Criteria andHg38LocationNotBetween(String value1, String value2) {
            addCriterion("hg38_location not between", value1, value2, "hg38Location");
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