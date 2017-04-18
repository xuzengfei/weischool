package com.bugframework.common.pojo;


import com.bugframework.common.annotation.DateColumn;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <p>Title:公共的pojo，里面包含一些常用的属性，给子类继承 </p>
 * <p>Description: </p>
 *
 * @author 许增飞
 * @date 2016-10-10 下午11:11:07
 */
@MappedSuperclass
public class IdEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 启用/禁用标记 1--启用  0--禁用
     */
    private Integer isenable;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 创建用户ID
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 更新用户ID
     */
    private String updateBy;
    /**
     * 删除时间
     */
    private Long delTime;
    /**
     * 删除标记 1--已删除 0--未删除
     */
    private Integer delFlag = 0;
    /**
     * 排序
     */
    private Integer orderby;

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

    @Column(name = "is_enable")
    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    @Column(name = "ct")
    @DateColumn
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "cb", length = 36)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "ut")
    @DateColumn
    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "ub")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "dt")
    @DateColumn
    public Long getDelTime() {
        return delTime;
    }

    public void setDelTime(Long delTime) {
        this.delTime = delTime;
    }

    @Column(name = "del_flag")
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Column(name = "order_by")
    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

}
