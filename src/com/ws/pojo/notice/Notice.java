package com.ws.pojo.notice;

import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.campus.Campus;

import javax.persistence.*;

/**
 * 公告管理
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/17
 */
@Entity
@Table(name = "ws_notice")
public class Notice extends IdEntity {
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告状态
     */
    private Integer status;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 发布校区
     */
    private Campus campus;
    /**
     * 头像
     */
    private String pic;
    /**
     * 七天属于新的公告：此标记最新公告，1--是最新公告 0--旧公告
     */
    private int isNew = 0;

    public Notice() {
    }

    public Notice(Campus campus, String content, Integer status, String title) {
        this.campus = campus;
        this.content = content;
        this.status = status;
        this.title = title;
    }

    /**
     * 获取 {@link #content}
     */
    @Column(name = "content", length = 255)
    public String getContent() {
        return content;
    }

    /**
     * 设置 {@link #content}
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 {@link #status}
     */
    @Column(name = "status", length = 2)
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 {@link #status}
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 {@link #title}
     */
    @Column(name = "title", length = 50)
    public String getTitle() {
        return title;
    }

    /**
     * 设置 {@link #title}
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 {@link #campus}
     */
    @ManyToOne
    @JoinColumn(name = "cp_id")
    public Campus getCampus() {
        return campus;
    }

    /**
     * 设置 {@link #campus}
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Transient
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Transient
    public int getIsNew() {
        if (super.getCreateTime() != null)
            if (System.currentTimeMillis() - super.getCreateTime() <= 604800000)//七天
                return 1;
        return isNew;
    }
}
