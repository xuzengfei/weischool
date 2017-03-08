package com.ws.pojo.coupon;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.consumer.Consumer;
import com.ws.pojo.student.Student;

import javax.persistence.*;

/**
 * 学生代金券
 *
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
@Entity
@Table(name = "ws_coupon")
public class Coupon extends IdEntity  implements Cloneable {
    /**
     * 编号
     */
    private String no;
    /**
     * 使用说明
     */
    private String remark;
    /**
     * 使用期限：开始时间
     */
    private Long st;
    /**
     * 使用期限：结束时间
     */
    private Long ed;
    /**
     * 使用类型：0--代金券 1--优惠券
     */
  //  private Integer useType;
    /**
     * 使用规则：代金的金额 
     */
    private String useRule;

    /**
     * 使用的学生
     */
    private Student student;

    /**
     * 学生ID（查询用）
     */
    private String stId;
    /**
     * 学生姓名（查询用）
     */
    private String stName;

    /**
     * 获取{@link Coupon#no}
     */
    @Column(name = "no", length = 50)
    public String getNo() {
        return no;
    }

    /**
     * 设置{@link Coupon#no}
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取{@link Coupon#remark}
     */
    @Column(name = "remark", length = 50)
    public String getRemark() {
        return remark;
    }

    /**
     * 设置{@link Coupon#remark}
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取{@link Coupon#st}
     */
    @Column(name = "st")
    public Long getSt() {
        return st;
    }

    /**
     * 设置{@link Coupon#st}
     */
    public void setSt(Long st) {
        this.st = st;
    }

    /**
     * 获取{@link Coupon#ed}
     */
    @Column(name = "ed")
    public Long getEd() {
        return ed;
    }

    /**
     * 设置{@link Coupon#ed}
     */
    public void setEd(Long ed) {
        this.ed = ed;
    }

   /* *//**
     * 获取{@link Coupon#useType}
     *//*
    @Column(name = "use_type")
    public Integer getUseType() {
        return useType;
    }

    *//**
     * 设置{@link Coupon#useType}
     *//*
    public void setUseType(Integer useType) {
        this.useType = useType;
    }*/

    /**
     * 获取{@link Coupon#useRule}
     */
    @Column(name = "use_rule", length = 50)
    public String getUseRule() {
        return useRule;
    }

    /**
     * 设置{@link Coupon#useRule}
     */
    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }

    /**
     * 获取{@link Coupon#student}
     */
    @ManyToOne
    @JoinColumn(name = "st_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * 获取{@link Consumer#stId}
     */
    @Transient
    @ForeKey(category = "student", column = "id")
    public String getStId() {
        if (this.student != null && this.student.getId() != null)
            return this.student.getId();
        return null;
    }

    /**
     * 设置{@link Consumer#stId}
     */
    public void setStId(String stId) {
        this.stId = stId;
    }

    @Transient
    @ForeKey(category = "student", column = "name",id = false)
    public String getStName() {
        if (this.student != null && this.student.getName() != null)
            return this.student.getName();
        return null;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }
    @Transient
    public boolean isAddIlegal(){
    	if(this.st==null||this.ed==null||this.student==null||this.useRule==null||this.no==null )
    		return false;
    				
    	return true;			
    }
    @Override  
    public Coupon clone() {  
        Coupon stu = null;  
        try{
            stu =(Coupon)super.clone();
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return stu;  
    }  
}
