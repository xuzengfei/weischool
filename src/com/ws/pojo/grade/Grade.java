package com.ws.pojo.grade;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.campus.Campus;

import javax.persistence.*;

/**
 * 班级
 *
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Entity
@Table(name = "ws_gra")
public class Grade extends IdEntity {
    /**
     * 班级名称
     */
    private String name;
    /**
     * 授课老师ID
     */
    private String teacher;
    /**
     * 授课老师名称
     */
    private String tcName;
    /**
     * 所属校区
     */
    private Campus campus;
    /**
     * 校区ID（查询使用的，不是ws_tc里面的属性）
     */
    private String cpId;


    public Grade() {
    }

    /**
     * 有参构造函数
     *
     * @param name    班级名称
     * @param teacher 授课老师
     */
    public Grade(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    /**
     * 获取{@link Grade#name}
     */
    @Column(name = "name", length = 20)
    public String getName() {
        return name;
    }

    /**
     * 设置{@link Grade#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取{@link Grade#teacher}
     */
    @Column(name = "tc_id", length = 36)
    public String getTeacher() {
        return teacher;
    }

    /**
     * 设置{@link Grade#teacher}
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Column(name = "tc_name", length = 20)
    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    /**
     * 获取{@link Grade#campus}
     */
    @ManyToOne
    @JoinColumn(name = "cp_id")
    public Campus getCampus() {
        return campus;
    }

    /**
     * 设置{@link Grade#campus}
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * 获取{@link Grade#cpId}
     */
    @ForeKey(category = "campus", column = "id")
    @Transient
    public String getCpId() {
        if (this.campus != null && this.campus.getId() != null)
            return this.campus.getId();
        return null;
    }

    /**
     * 设置{@link Grade#cpId}
     */
    public void setCpId(String cpId) {
        this.cpId = cpId;
    }
}
