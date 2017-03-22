package com.ws.pojo.experience;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.student.Student;

import javax.persistence.*;
import java.util.List;

/**
 * 成长经历
 *
 * @author Administrator
 */
@Entity
@Table(name = "ws_expe")
public class Experience extends IdEntity {
    /**
     * 活动经历
     */
    private String remark;
    /**
     * 活动时间
     */
    private Long expeTime;
    /**
     * 学生
     */
    private Student student;
    /**
     * 学生ID
     */
    private String stId;
    /**
     * 学生名称
     */
    private String stName;
    /**
     * 获得前台传过来的图片ID,可能存在多个ID
     */

    private String[] pics;
    /**
     * 图片路径集合
     */
    private List<String> picPaths;

    @Transient
    public Experience remak(String remark) {
        this.remark = remark;
        return this;
    }

    @Transient
    public Experience remak(Long expeTime) {
        this.expeTime = expeTime;
        return this;
    }

    @Transient
    public Experience remak(Student student) {
        this.student = student;
        return this;
    }

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "expe_time")
    public Long getExpeTime() {
        return expeTime;
    }

    public void setExpeTime(Long expeTime) {
        this.expeTime = expeTime;
    }

    @ManyToOne
    @JoinColumn(name = "st_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    @Transient
    @ForeKey(category = "student", column = "id")
    public String getStId() {
        if (this.student != null && this.student.getId() != null)
            return this.student.getId();
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    @Transient
    @ForeKey(category = "student", column = "name", id = false)
    public String getStName() {
        if (this.student != null && this.student.getName() != null)
            return this.student.getName();
        return null;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Transient
    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }
    @Transient
    public List<String> getPicPaths() {
        return picPaths;
    }

    public void setPicPaths(List<String> picPaths) {
        this.picPaths = picPaths;
    }
}
