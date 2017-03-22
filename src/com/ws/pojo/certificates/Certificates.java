package com.ws.pojo.certificates;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.student.Student;

import javax.persistence.*;

/**
 * 荣誉证书
 *
 * @author 许增飞
 */
@Entity
@Table(name = "ws_cer")
public class Certificates extends IdEntity {
    /**
     * 证书编号
     */
    private String no;
    /**
     * 证书项目
     */
    private String remark;
    /**
     * 收到证书时间
     */
    private Long cerTime;
    /**
     * 学生
     */
    private Student student;
    /**
     * 学生名称
     */
    private String stName;
    /**
     * 学生
     */
    private String stId;
    /**
     * 获得前台传过来的图片ID,可能存在多个ID
     */

    private String[] pics;

    private String pic;

    @Transient
    public Certificates no(String no) {
        this.no = no;
        return this;
    }

    @Transient
    public Certificates remark(String remark) {
        this.remark = remark;
        return this;
    }

    @Transient
    public Certificates cerTime(Long cerTime) {
        this.cerTime = cerTime;
        return this;
    }

    @Transient
    public Certificates cerTime(Student student) {
        this.student = student;
        return this;
    }

    @Column(name = "no", length = 100)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Column(name = "remark", length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "cer_time")
    public Long getCerTime() {
        return cerTime;
    }

    public void setCerTime(Long cerTime) {
        this.cerTime = cerTime;
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
    @ForeKey(category = "student", column = "id", id = true)
    public String getStId() {
        if (this.student != null && this.student.getId() != null)
            return this.student.getId();
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }
    @Override
    public String toString() {
        return "Certificates [no=" + no + ", remark=" + remark + ", cerTime="
                + cerTime + ", student=" + student + ", stName=" + stName + "]";
    }
    @Transient
    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }
    @Transient
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


}
