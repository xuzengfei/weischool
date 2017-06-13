package com.ws.pojo.student;

import com.bugframework.common.annotation.DateColumn;
import com.bugframework.common.annotation.ForeKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author 许增飞
 * @description 学生微信端信息绑定表：openId和Student id 绑定。允许一个openID绑定多个Student
 * @datetime 2017/6/12 14:15
 * @return
 */
@Entity
@Table(name = "ws_st_openid")
public class StudentOpenIdVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 常用的校区
     */
    private String cpId;
    /**
     * 创建时间
     */
    private Long ct;
    /**
     * 学生基本信息
     */
    private Student student;

    /**
     * 学生姓名（查询用）
     */
    private String stName;

    /**
     * 学生学号
     */
    private String stNo;

    public StudentOpenIdVo() {
    }


    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(name = "id", length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "st_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    @Column(name = "cp_id", length = 36)
    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    @DateColumn
    @Column(name = "ct")
    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
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
    @ForeKey(category = "student", column = "no", id = false)
    public String getStNo() {
        if (this.student != null && this.student.getNo() != null)
            return this.student.getNo();
        return null;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }
}
