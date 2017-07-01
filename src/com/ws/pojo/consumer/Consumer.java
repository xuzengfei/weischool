package com.ws.pojo.consumer;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.student.StudentGrade;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 缴费清单
 *
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
@Entity
@Table(name = "ws_consumer")
public class Consumer extends IdEntity {
	/**
	 * 项目类型:1--课时费 2--活动报名费 3--其它费用
	 */
	private Integer projectType;
    /**
     * 缴费项目
     */
    private String project;
    /**
     * 缴费金额
     */
    private String amount;
    /**
     * 缴费方式说明
     */
    private String payType;
    /**d
     * 缴费时间
     */
    private Long payTime;
    /**
     * 使用券种描述
     */
    private String remark;
    /**
     * 操作员名称
     */
    private String createName;
    /**
     * 学生
     */
    private StudentGrade student;
    /**
     * 学生名称
     */
    //   private String stName;
    /**
     * 课时
     */	
    private Integer classNum;
    /**
     * 学生ID（查询用）
     */
    private String stId;
    
    @Column(name = "p_type")
    public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	/**
     * 获取{@link Consumer#project}
     */
    @Column(name = "project", length = 50)
    public String getProject() {
        return project;
    }

    /**
     * 设置{@link Consumer#project}
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * 获取{@link Consumer#amount}
     */
    @Column(name = "amount",length=20)
    public String getAmount() {
        return amount;
    }

    /**
     * 设置{@link Consumer#amount}
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 获取{@link Consumer#payType}
     */
    @Column(name = "pay_type", length = 50)
    public String getPayType() {
        return payType;
    }

    /**
     * 设置{@link Consumer#payType}
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取{@link Consumer#coupNo}
     */
    @Column(name = "coup_rk", length = 50)
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    /**
     * 获取{@link Consumer#createName}
     */
    @Column(name = "ct_name", length = 50)
    public String getCreateName() {
        return createName;
    }

   

	/**
     * 设置{@link Consumer#createName}
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }


    @ManyToOne
    @JoinColumn(name = "st_id")
    public StudentGrade getStudent() {
        return student;
    }

    public void setStudent(StudentGrade student) {
        this.student = student;
    }

    /*@Transient
    @ForeKey(category = "student", column = "name", id = false)
    public String getStName() {
        if (this.student != null && this.student.getName() != null)
            return this.student.getName();
        return null;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }
*/

    /**
     * 获取{@link Consumer#classNum}
     */
    @Column(name = "class_num")
    public Integer getClassNum() {
        return classNum;
    }

    /**
     * 设置{@link Consumer#classNum}
     */
    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    /**
     * 获取{@link Consumer#stId}
     */
    @Transient
    @ForeKey(category = "student", column = "id")
    public String getStId() {
        if(this.student!=null&&this.student.getId()!=null)
            return this.student.getId();
        return null;
    }

    /**
     * 设置{@link Consumer#stId}
     */
    public void setStId(String stId) {
        this.stId = stId;
    }

    @Column(name="pay_time")
	public Long getPayTime() {
		return payTime;
	}

	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}

    @Override
    public String toString() {
        return "Consumer{" +
                "projectType=" + projectType +
                ", project='" + project + '\'' +
                ", amount='" + amount + '\'' +
                ", payType='" + payType + '\'' +
                ", payTime=" + payTime +
                ", remark='" + remark + '\'' +
                ", createName='" + createName + '\'' +
                ", student=" + student +
                ", classNum=" + classNum +
                ", stId='" + stId + '\'' +
                '}';
    }
}
