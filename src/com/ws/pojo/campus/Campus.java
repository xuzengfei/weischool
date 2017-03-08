package com.ws.pojo.campus;

import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 校区管理
 *
 * @author Administrator
 */
@Entity
@Table(name = "ws_cp")
public class Campus extends IdEntity {
    /**
     * 校区名称
     */
    private String name;

    public Campus() {
    }

    public Campus(String id) {
        this.setId(id);
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
