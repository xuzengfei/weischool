package com.ws.pojo.grade;

import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 费用模板
 * Created by admin on 2017/3/17.
 */
@Entity
@Table(name = "ws_gra_cost_tpl")
public class GradeCostTpl extends IdEntity {
    /**
     * 班级ID
     */
    private String gradeId;
    /**
     * 课时
     */
    private Integer classNum;
    /**
     * 费用
     */
    private String amount;
    /**
     * 简述
     */
    private String remark;

    public GradeCostTpl gradeId(String gradeId) {
        this.gradeId = gradeId;
        return this;
    }

    public GradeCostTpl classNum(Integer classNum) {
        this.classNum = classNum;
        return this;
    }

    public GradeCostTpl amount(String amount) {
        this.amount = amount;
        return this;
    }

    public GradeCostTpl remark(String remark) {
        this.amount = remark;
        return this;
    }

    @Column(name = "gra_id", length = 32)
    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    @Column(name = "class_num")
    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    @Column(name = "amount", length = 32)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Column(name = "remark", length = 32)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
