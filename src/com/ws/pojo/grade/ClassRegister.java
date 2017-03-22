package com.ws.pojo.grade;

import com.bugframework.common.annotation.DateColumn;
import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 上课记录
 * Created by Administrator on 2017/2/28.
 */
@Entity
@Table(name = "ws_cl_reg")
public class ClassRegister extends IdEntity {
    /**
     * 学生ID
     */
    private String stId;
    /**
     * 学生名称
     */
    private String stName;
    /**
     * 教师ID
     */
    private String tcId;
    /**
     * 教师名称
     */
    private String tcName;
    /**
     * 班级ID
     */
    private String gradeId;
    /**
     * 记录类型:1--准时 2--请假 -1旷课
     */
    private Integer status;

    public ClassRegister() {
    }

    public ClassRegister(String stId, String stName, String tcId, String tcName, String gradeId, Integer status) {
        this.stId = stId;
        this.stName = stName;
        this.tcId = tcId;
        this.tcName = tcName;
        this.gradeId = gradeId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassRegister{" +
                "stId='" + stId + '\'' +
                ", stName='" + stName + '\'' +
                ", tcId='" + tcId + '\'' +
                ", tcName='" + tcName + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", status=" + status +
                '}';
    }

    @Column(name = "st_id", length = 32)
    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    @Column(name = "st_name", length = 64)
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Column(name = "tc_id", length = 32)
    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }

    @Column(name = "tc_name", length = 64)
    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    @Column(name = "gra_id", length = 32)
    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @DateColumn
    @Override
    public void setCreateTime(Long createTime) {
        super.setCreateTime(createTime);
    }
}
