package com.xzpx_zc.pojo;

import java.util.ArrayList;
import java.util.List;

public class ZcNavExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZcNavExample() {
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

        public Criteria andNavIdIsNull() {
            addCriterion("nav_id is null");
            return (Criteria) this;
        }

        public Criteria andNavIdIsNotNull() {
            addCriterion("nav_id is not null");
            return (Criteria) this;
        }

        public Criteria andNavIdEqualTo(Integer value) {
            addCriterion("nav_id =", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotEqualTo(Integer value) {
            addCriterion("nav_id <>", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdGreaterThan(Integer value) {
            addCriterion("nav_id >", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("nav_id >=", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdLessThan(Integer value) {
            addCriterion("nav_id <", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdLessThanOrEqualTo(Integer value) {
            addCriterion("nav_id <=", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdIn(List<Integer> values) {
            addCriterion("nav_id in", values, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotIn(List<Integer> values) {
            addCriterion("nav_id not in", values, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdBetween(Integer value1, Integer value2) {
            addCriterion("nav_id between", value1, value2, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotBetween(Integer value1, Integer value2) {
            addCriterion("nav_id not between", value1, value2, "navId");
            return (Criteria) this;
        }

        public Criteria andNavUseridIsNull() {
            addCriterion("nav_userId is null");
            return (Criteria) this;
        }

        public Criteria andNavUseridIsNotNull() {
            addCriterion("nav_userId is not null");
            return (Criteria) this;
        }

        public Criteria andNavUseridEqualTo(Integer value) {
            addCriterion("nav_userId =", value, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridNotEqualTo(Integer value) {
            addCriterion("nav_userId <>", value, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridGreaterThan(Integer value) {
            addCriterion("nav_userId >", value, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("nav_userId >=", value, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridLessThan(Integer value) {
            addCriterion("nav_userId <", value, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridLessThanOrEqualTo(Integer value) {
            addCriterion("nav_userId <=", value, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridIn(List<Integer> values) {
            addCriterion("nav_userId in", values, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridNotIn(List<Integer> values) {
            addCriterion("nav_userId not in", values, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridBetween(Integer value1, Integer value2) {
            addCriterion("nav_userId between", value1, value2, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("nav_userId not between", value1, value2, "navUserid");
            return (Criteria) this;
        }

        public Criteria andNavNameIsNull() {
            addCriterion("nav_name is null");
            return (Criteria) this;
        }

        public Criteria andNavNameIsNotNull() {
            addCriterion("nav_name is not null");
            return (Criteria) this;
        }

        public Criteria andNavNameEqualTo(String value) {
            addCriterion("nav_name =", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotEqualTo(String value) {
            addCriterion("nav_name <>", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameGreaterThan(String value) {
            addCriterion("nav_name >", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameGreaterThanOrEqualTo(String value) {
            addCriterion("nav_name >=", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameLessThan(String value) {
            addCriterion("nav_name <", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameLessThanOrEqualTo(String value) {
            addCriterion("nav_name <=", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameLike(String value) {
            addCriterion("nav_name like", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotLike(String value) {
            addCriterion("nav_name not like", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameIn(List<String> values) {
            addCriterion("nav_name in", values, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotIn(List<String> values) {
            addCriterion("nav_name not in", values, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameBetween(String value1, String value2) {
            addCriterion("nav_name between", value1, value2, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotBetween(String value1, String value2) {
            addCriterion("nav_name not between", value1, value2, "navName");
            return (Criteria) this;
        }

        public Criteria andNavParentidIsNull() {
            addCriterion("nav_parentId is null");
            return (Criteria) this;
        }

        public Criteria andNavParentidIsNotNull() {
            addCriterion("nav_parentId is not null");
            return (Criteria) this;
        }

        public Criteria andNavParentidEqualTo(Integer value) {
            addCriterion("nav_parentId =", value, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidNotEqualTo(Integer value) {
            addCriterion("nav_parentId <>", value, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidGreaterThan(Integer value) {
            addCriterion("nav_parentId >", value, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("nav_parentId >=", value, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidLessThan(Integer value) {
            addCriterion("nav_parentId <", value, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidLessThanOrEqualTo(Integer value) {
            addCriterion("nav_parentId <=", value, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidIn(List<Integer> values) {
            addCriterion("nav_parentId in", values, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidNotIn(List<Integer> values) {
            addCriterion("nav_parentId not in", values, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidBetween(Integer value1, Integer value2) {
            addCriterion("nav_parentId between", value1, value2, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("nav_parentId not between", value1, value2, "navParentid");
            return (Criteria) this;
        }

        public Criteria andNavStatucIsNull() {
            addCriterion("nav_statuc is null");
            return (Criteria) this;
        }

        public Criteria andNavStatucIsNotNull() {
            addCriterion("nav_statuc is not null");
            return (Criteria) this;
        }

        public Criteria andNavStatucEqualTo(Integer value) {
            addCriterion("nav_statuc =", value, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucNotEqualTo(Integer value) {
            addCriterion("nav_statuc <>", value, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucGreaterThan(Integer value) {
            addCriterion("nav_statuc >", value, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucGreaterThanOrEqualTo(Integer value) {
            addCriterion("nav_statuc >=", value, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucLessThan(Integer value) {
            addCriterion("nav_statuc <", value, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucLessThanOrEqualTo(Integer value) {
            addCriterion("nav_statuc <=", value, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucIn(List<Integer> values) {
            addCriterion("nav_statuc in", values, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucNotIn(List<Integer> values) {
            addCriterion("nav_statuc not in", values, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucBetween(Integer value1, Integer value2) {
            addCriterion("nav_statuc between", value1, value2, "navStatuc");
            return (Criteria) this;
        }

        public Criteria andNavStatucNotBetween(Integer value1, Integer value2) {
            addCriterion("nav_statuc not between", value1, value2, "navStatuc");
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