package com.ws.pojo.student;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.campus.Campus;
import com.ws.pojo.grade.Grade;

import javax.persistence.*;

/**
 * 学生班级信息
 *
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Entity
@Table(name = "ws_st_gra")
public class StudentGrade extends IdEntity {
    /**
     * 课程顾问
     */
    private String adviser;
    /**
     * 所属专业
     */
    private String major;
    /**
     * 备注
     */
    private String remark;
    /**
     * 报名时间
     */
    private Long signTime;
    /**
     * 剩余课时
     */
    private Integer restClass;
    /**
     * 校区
     */
    private Campus campus;
    /**
     * 班级
     */
    private Grade grade;
    /**
     * 学生
     */
    private Student student;
    /**
     * 校区ID（用于查询）
     */
    private String cpId;
    /**
     * 班级ID（用于查询）
     */
    private String gradeId;
    /**
     * 学生账号（用于查询）
     */
    private String studentNo;
    /**
     * 学生姓名（用于查询）
     */
    private String studentName;

    public StudentGrade() {
    }

    public StudentGrade(String adviser, String major, String remark, Integer restClass, Campus campus, Grade grade) {
        this.adviser = adviser;
        this.major = major;
        this.remark = remark;
        this.restClass = restClass;
        this.campus = campus;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "adviser='" + adviser + '\'' +
                ", major='" + major + '\'' +
                ", remark='" + remark + '\'' +
                ", signTime=" + signTime +
                ", restClass=" + restClass +
                ", campus=" + campus +
                ", grade=" + grade +
                ", student=" + student +
                ", cpId='" + cpId + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    public StudentGrade id(String id){
        this.setId(id);
        return this;
    }
    public StudentGrade gradeId(String gradeId){
        this.setGradeId(gradeId);
        return this;
    }

    /**
     * 获取{@link StudentGrade#adviser}
     */
    @Column(name = "adviser", length = 50)
    public String getAdviser() {
        return adviser;
    }

    /**
     * 设置{@link StudentGrade#adviser}
     */
    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    /**
     * 获取{@link StudentGrade#major}
     */
    @Column(name = "major", length = 50)
    public String getMajor() {
        return major;
    }

    /**
     * 设置{@link StudentGrade#major}
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 获取{@link StudentGrade#remark}
     */
    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    /**
     * 设置{@link StudentGrade#remark}
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取{@link StudentGrade#restClass}
     */
    @Column(name = "rest_class")
    public Integer getRestClass() {
        return restClass;
    }

    /**
     * 设置{@link StudentGrade#restClass}
     */
    public void setRestClass(Integer restClass) {
        this.restClass = restClass;
    }

    /**
     * 获取{@link StudentGrade#campus}
     */
    @ManyToOne
    @JoinColumn(name = "cp_id")
    public Campus getCampus() {
        return campus;
    }

    /**
     * 设置{@link StudentGrade#campus}
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * 获取{@link StudentGrade#grade}
     */
    @ManyToOne
    @JoinColumn(name = "gra_id")
    public Grade getGrade() {
        return grade;
    }

    /**
     * 设置{@link StudentGrade#grade}
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * 获取{@link StudentGrade#student}
     */
    @ManyToOne
    @JoinColumn(name = "st_id")
    public Student getStudent() {
        return student;
    }

    /**
     * 设置{@link StudentGrade#student}
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * 获取{@link StudentGrade#signTime}
     */
    @Column(name = "st")
    public Long getSignTime() {
        return signTime;
    }

    /**
     * 设置{@link StudentGrade#signTime}
     */
    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取{@link StudentGrade#cpId}
     */
    @Transient
    @ForeKey(category = "campus", column = "id")
    public String getCpId() {
        if (this.campus != null && this.campus.getId() != null)
            return this.campus.getId();
        return null;
    }

    /**
     * 设置{@link StudentGrade#cpId}
     */
    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    /**
     * 获取{@link StudentGrade#gradeId}
     */
    @Transient
    @ForeKey(category = "grade", column = "id")
    public String getGradeId() {
        if (this.grade != null && this.grade.getId() != null)
            return this.grade.getId();
        return null;
    }

    /**
     * 设置{@link StudentGrade#gradeId}
     */
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 获取{@link StudentGrade#studentNo}
     */
    @Transient
    @ForeKey(category = "student", column = "no",id = false)
    public String getStudentNo() {
        if (this.student != null && this.student.getNo() != null)
            return this.student.getNo();
        return null;
    }

    /**
     * 设置{@link StudentGrade#studentNo}
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 获取{@link StudentGrade#studentName}
     */
    @Transient
    @ForeKey(category = "student", column = "name",id = false)
    public String getStudentName() {
        if (this.student != null && this.student.getName() != null)
            return this.student.getName();
        return null;
    }

    /**
     * 设置{@link StudentGrade#studentName}
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
