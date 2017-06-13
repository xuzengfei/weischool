package com.ws.pojo.teacher;

import com.bugframework.common.annotation.DateColumn;
import com.bugframework.common.annotation.ForeKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author 许增飞
 * @description 老师微信绑定
 * @datetime 2017/6/12 15:04
 */
@Entity
@Table(name = "ws_tc_openid")
public class TeacherOpenIdVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 创建ID
     */
    private Long ct;
    /**
     * 关联老师信息表
     */
    private Teacher teacher;
    /**
     * 老师名称（用于查询）
     */
    private String tcName;
    /**
     * 老师工号
     */
    private String tcNo;

    public TeacherOpenIdVo() {
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


    @DateColumn
    @Column(name = "ct")
    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }

    @ManyToOne
    @JoinColumn(name = "tc_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Transient
    @ForeKey(category = "teacher", column = "name")
    public String getTcName() {
        if (this.teacher != null && this.teacher.getName() != null)
            return this.teacher.getName();
        return null;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    @Transient
    @ForeKey(category = "teacher", column = "emNo")
    public String getTcNo() {
        if (this.teacher != null && this.teacher.getEmNo() != null)
            return this.teacher.getEmNo();
        return null;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }
}
