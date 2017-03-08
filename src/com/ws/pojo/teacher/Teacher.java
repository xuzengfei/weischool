package com.ws.pojo.teacher;

import com.bugframework.common.pojo.IdEntity;
import com.ws.pojo.campus.Campus;

import javax.persistence.*;

/**
 * 教师类
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/18
 */
@Entity
@Table(name = "ws_tc")
public class Teacher extends IdEntity {
    /**
     * 教师工号
     */
    private String emNo;
    /**
     * 教师名称
     */
    private String name;
    /**
     * 教师身份证号
     */
    private String idNo;
    /**
     * 教师性别
     */
    private String sex;
    /**
     *
     */
    private String tel;
    /**
     * 教师微信号
     */
    private String weiNo;
    /**
     * 教师微信公众号id
     */
    private String openId;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 所属校区
     */
    private Campus campus;


    /**
     * 获取 {@link #campus}
     */
    @ManyToOne
    @JoinColumn(name="cp_id")
    public Campus getCampus() {
        return campus;
    }

    /**
     * 设置 {@link #campus}
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * 获取 {@link #emNo}
     */
    @Column(name="emNo",length=50)
    public String getEmNo() {
        return emNo;
    }

    /**
     * 设置 {@link #emNo}
     */
    public void setEmNo(String emNo) {
        this.emNo = emNo;
    }

    /**
     * 获取 {@link #idNo}
     */
    @Column(name="idNo",length=50)
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置 {@link #idNo}
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * 获取 {@link #name}
     */
    @Column(name = "name",length = 50)
    public String getName() {
        return name;
    }

    /**
     * 设置 {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 {@link #openId}
     */
    @Column(name = "openId",length = 50)
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置 {@link #openId}
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取 {@link #password}
     */
    @Column(name = "password",length = 50)
    public String getPassword() {
        return password;
    }

    /**
     * 设置 {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 {@link #sex}
     */
    @Column(name = "sex",length = 2)
    public String getSex() {
        return sex;
    }

    /**
     * 设置 {@link #sex}
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取 {@link #tel}
     */
    @Column(name = "tel",length = 50)
    public String getTel() {
        return tel;
    }

    /**
     * 设置 {@link #tel}
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取 {@link #weiNo}
     */
    @Column(name = "weiNo",length = 50)
    public String getWeiNo() {
        return weiNo;
    }

    /**
     * 设置 {@link #weiNo}
     */
    public void setWeiNo(String weiNo) {
        this.weiNo = weiNo;
    }
}
