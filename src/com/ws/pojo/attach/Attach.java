package com.ws.pojo.attach;

import com.bugframework.common.pojo.IdEntity;
import com.ws.util.ModuleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 附件ID
 * Created by admin on 2017/1/10.
 */

@Entity
@Table(name = "ws_attach")
public class Attach extends IdEntity {
    /**
     * 附件名称
     */
    private String name;
    /**
     * 附件地址
     */
    private String path;
    /**
     * 附件大小
     */
    private Long size;
    /**
     * 附件所属模块ID
     */
    private String moduleId;
    /**
     * 附件所属模块类型
     */
    private String moduleType;

    public Attach() {
    }

    public Attach(String moduleId, Integer delFlag) {
        this.setDelFlag(delFlag);
        this.moduleId = moduleId;
    }

    /**
     * 模块类型枚举类
     */
    //   private ModuleType moduleTypeEum;
    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "f_path", length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Column(name = "f_size")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Column(name = "m_id", length = 36)
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "m_type", length = 20)
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Transient
    public ModuleType getModuleTypeEum(String moduleType) {
        for (ModuleType m : ModuleType.values()) {
            if (m.getValue().equals(moduleType)) return m;
        }
        return null;
    }

    @Transient
    public boolean isAddValid() {
        if (this.name == null || this.path == null || this.size == null || this.moduleId == null || this.moduleType == null)
            return false;
        return true;
    }

    @Transient
    public boolean isUpdValid() {
        if (this.getId() == null)
            return false;
        return true;
    }

}
