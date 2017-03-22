package com.ws.pojo.campuslife;

import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 校园生活
 * Created by Administrator on 2017/3/9.
 */
@Entity
@Table(name = "ws_cp_life")
public class CpLife extends IdEntity {
    private String cpId;//校区ID
    private String title;//标题
    private String remark;//描述
    private Long st;//活动时间
    private String pic;//头像地址

    @Column(name = "cp_id", length = 32)
    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    @Column(name = "title", length = 32)
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

    @Column(name = "st")
    public Long getSt() {
        return st;
    }

    public void setSt(Long st) {
        this.st = st;
    }

    @Transient
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
