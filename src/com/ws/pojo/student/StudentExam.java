package com.ws.pojo.student;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;

import javax.persistence.*;

/**
 * 学生考试表
 * Created by Administrator on 2017/3/7.
 */
@Entity
@Table(name ="ws_st_exam" )
public class StudentExam  extends IdEntity{
    /**
     * 描述
     */
    private String remark;
    /**
     * 标题
     */
    private String title;
    /**
     * 考试时间
     */
    private Long exTime;
    /**
     * 考试状态：1--通过 0--不通过
     */
    private  Integer status;
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
    @Column(name = "remark", length = 100,nullable = false)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name = "title", length = 50,nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "ex_time",nullable = false)
    public Long getExTime() {
        return exTime;
    }

    public void setExTime(Long exTime) {
        this.exTime = exTime;
    }
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
