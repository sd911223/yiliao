package com.platform.model;

import java.util.ArrayList;
import java.util.List;

public class LiteratureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LiteratureExample() {
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

        public Criteria andLiteratureIdIsNull() {
            addCriterion("literature_id is null");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdIsNotNull() {
            addCriterion("literature_id is not null");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdEqualTo(Integer value) {
            addCriterion("literature_id =", value, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdNotEqualTo(Integer value) {
            addCriterion("literature_id <>", value, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdGreaterThan(Integer value) {
            addCriterion("literature_id >", value, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("literature_id >=", value, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdLessThan(Integer value) {
            addCriterion("literature_id <", value, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdLessThanOrEqualTo(Integer value) {
            addCriterion("literature_id <=", value, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdIn(List<Integer> values) {
            addCriterion("literature_id in", values, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdNotIn(List<Integer> values) {
            addCriterion("literature_id not in", values, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdBetween(Integer value1, Integer value2) {
            addCriterion("literature_id between", value1, value2, "literatureId");
            return (Criteria) this;
        }

        public Criteria andLiteratureIdNotBetween(Integer value1, Integer value2) {
            addCriterion("literature_id not between", value1, value2, "literatureId");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameIsNull() {
            addCriterion("periodical_name is null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameIsNotNull() {
            addCriterion("periodical_name is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameEqualTo(String value) {
            addCriterion("periodical_name =", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotEqualTo(String value) {
            addCriterion("periodical_name <>", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameGreaterThan(String value) {
            addCriterion("periodical_name >", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameGreaterThanOrEqualTo(String value) {
            addCriterion("periodical_name >=", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameLessThan(String value) {
            addCriterion("periodical_name <", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameLessThanOrEqualTo(String value) {
            addCriterion("periodical_name <=", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameLike(String value) {
            addCriterion("periodical_name like", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotLike(String value) {
            addCriterion("periodical_name not like", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameIn(List<String> values) {
            addCriterion("periodical_name in", values, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotIn(List<String> values) {
            addCriterion("periodical_name not in", values, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameBetween(String value1, String value2) {
            addCriterion("periodical_name between", value1, value2, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotBetween(String value1, String value2) {
            addCriterion("periodical_name not between", value1, value2, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberIsNull() {
            addCriterion("periodical_number is null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberIsNotNull() {
            addCriterion("periodical_number is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberEqualTo(String value) {
            addCriterion("periodical_number =", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberNotEqualTo(String value) {
            addCriterion("periodical_number <>", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberGreaterThan(String value) {
            addCriterion("periodical_number >", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberGreaterThanOrEqualTo(String value) {
            addCriterion("periodical_number >=", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberLessThan(String value) {
            addCriterion("periodical_number <", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberLessThanOrEqualTo(String value) {
            addCriterion("periodical_number <=", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberLike(String value) {
            addCriterion("periodical_number like", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberNotLike(String value) {
            addCriterion("periodical_number not like", value, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberIn(List<String> values) {
            addCriterion("periodical_number in", values, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberNotIn(List<String> values) {
            addCriterion("periodical_number not in", values, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberBetween(String value1, String value2) {
            addCriterion("periodical_number between", value1, value2, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNumberNotBetween(String value1, String value2) {
            addCriterion("periodical_number not between", value1, value2, "periodicalNumber");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeIsNull() {
            addCriterion("publishing_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeIsNotNull() {
            addCriterion("publishing_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeEqualTo(String value) {
            addCriterion("publishing_time =", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeNotEqualTo(String value) {
            addCriterion("publishing_time <>", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeGreaterThan(String value) {
            addCriterion("publishing_time >", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeGreaterThanOrEqualTo(String value) {
            addCriterion("publishing_time >=", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeLessThan(String value) {
            addCriterion("publishing_time <", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeLessThanOrEqualTo(String value) {
            addCriterion("publishing_time <=", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeLike(String value) {
            addCriterion("publishing_time like", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeNotLike(String value) {
            addCriterion("publishing_time not like", value, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeIn(List<String> values) {
            addCriterion("publishing_time in", values, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeNotIn(List<String> values) {
            addCriterion("publishing_time not in", values, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeBetween(String value1, String value2) {
            addCriterion("publishing_time between", value1, value2, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andPublishingTimeNotBetween(String value1, String value2) {
            addCriterion("publishing_time not between", value1, value2, "publishingTime");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
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