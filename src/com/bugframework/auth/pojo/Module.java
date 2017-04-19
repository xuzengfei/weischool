package com.bugframework.auth.pojo;


import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auth_module")
public class Module extends IdEntity implements java.io.Serializable {
    /**
     * 父节点ID
     */
    private String pid;
    /**
     * 模块名称
     */
    private String name;
    /**
     * 地址
     */
    private String url;
    /**
     * 图标名称
     */
    private String icon;
    /**
     * 第几层：顶层是0
     */
    private Short floor;
    /**
     * 否是为不可删除栏目1--是0--否
     */
    private Short isAdmin;
    /**
     * 快捷方式 1--是 0--否
     */
    private Short shortcut;

    public Module() {
    }

    public Module(String id) {
        this.setId(id);
    }

    @Column(name = "pid", length = 36, nullable = false)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "name", length = 20, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url", length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "floor", length = 1)
    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    @Column(name = "is_admin", length = 1)
    public Short getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Column(name = "icon", length = 100)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "shortcut", length = 1,columnDefinition = "0")
    public Short getShortcut() {
        return shortcut;
    }

    public void setShortcut(Short shortcut) {
        this.shortcut = shortcut;
    }
}
