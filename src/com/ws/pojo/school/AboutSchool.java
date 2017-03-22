package com.ws.pojo.school;

import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 关于学校
 * Created by admin on 2017/3/14.
 */
@Entity
@Table(name = "ws_sc_ab")
public class AboutSchool extends IdEntity {
    private String title;
    private String remark;

    @Column(name = "title", length = 64)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
