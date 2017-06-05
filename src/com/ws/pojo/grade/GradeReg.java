package com.ws.pojo.grade;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 上课签到
 * Created by admin on 2017/4/7.
 */
@Entity
@Table(name="ws_gra_reg")
public class GradeReg {
    /**
     * 主键
     */
    private String id;
    /**
     * 课程ID
     */
    private String gradeId;
    /**
     * 课程名称
     */
    private String gradeName;
    /**
     * 老师ID
     */
    private String tcId;
    /**
     * 老师名称
     */
    private String tcName;
    /**
     * 学生ID
     */
    private String stId;
    /**
     * 学生名称
     */
    private String stName;
    /**
     * 创建时间
     */
    private Long ct;
    /**
     * 状态：1-准 2-请 3-旷 4-迟
     */
    private Short status;
    /**
     * 课程班次时间表Id
     */
    private String gtId;

    public GradeReg() {
    }

    public GradeReg(String id, String gradeId, String gradeName, String tcId, String tcName, String stId, String stName, Long ct, Short status,String gtId) {
        this.id = id;
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.tcId = tcId;
        this.tcName = tcName;
        this.stId = stId;
        this.stName = stName;
        this.ct = ct;
        this.status = status;
        this.gtId=gtId;
    }

    @Override
    public String toString() {
        return "GradeReg{" +
                "id='" + id + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", tcId='" + tcId + '\'' +
                ", tcName='" + tcName + '\'' +
                ", stId='" + stId + '\'' +
                ", stName='" + stName + '\'' +
                ", ct=" + ct +
                ", status=" + status +
                '}';
    }

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "gra_id", length = 32)
    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    @Column(name = "gra_name", length = 32)
    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Column(name = "tc_id", length = 32)
    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }

    @Column(name = "tc_name", length = 32)
    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    @Column(name = "st_id", length = 32)
    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    @Column(name = "st_name", length = 32)
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Column(name = "ct")
    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }

    @Column(name = "status")
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Column(name = "gt_id", length = 32)
    public String getGtId() {
        return gtId;
    }

    public void setGtId(String gtId) {
        this.gtId = gtId;
    }
}
