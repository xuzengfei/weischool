package com.ws.pojo.student;

import com.bugframework.common.pojo.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 学生基本信息
 *
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Entity
@Table(name = "ws_st")
public class Student extends IdEntity {
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private String no;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系人号码
     */
    private String contactsTel;
    /**
     * 微信号码
     */
    private String weiNo;
    /**
     * 微信公众号ID
     */
    private String openId;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 头像
     */
    private String pic;

    public Student() {
    }

    public Student(String name, String no, String sex, String idNo, String contacts, String contactsTel, String weiNo, String openId, String password) {
        this.name = name;
        this.no = no;
        this.sex = sex;
        this.idNo = idNo;
        this.contacts = contacts;
        this.contactsTel = contactsTel;
        this.weiNo = weiNo;
        this.openId = openId;
        this.password = password;
    }

    public Student(String id) {
        setId(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", sex='" + sex + '\'' +
                ", idNo='" + idNo + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contactsTel='" + contactsTel + '\'' +
                ", weiNo='" + weiNo + '\'' +
                ", openId='" + openId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * 获取{@link Student#name}
     */
    @Column(name = "name", length = 50)
    public String getName() {
        return name;
    }

    /**
     * 设置{@link Student#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取{@link Student#no}
     */
    @Column(name = "no", length = 50)
    public String getNo() {
        return no;
    }

    /**
     * 设置{@link Student#no}
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取{@link Student#sex}
     */
    @Column(name = "sex", length = 2)
    public String getSex() {
        return sex;
    }

    /**
     * 设置{@link Student#sex}
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取{@link Student#idNo}
     */
    @Column(name = "idno", length = 50)
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置{@link Student#idNo}
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * 获取{@link Student#contacts}
     */
    @Column(name = "contacts", length = 50)
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置{@link Student#contacts}
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取{@link Student#contactsTel}
     */
    @Column(name = "c_tel", length = 20)
    public String getContactsTel() {
        return contactsTel;
    }

    /**
     * 设置{@link Student#contactsTel}
     */
    public void setContactsTel(String contactsTel) {
        this.contactsTel = contactsTel;
    }

    /**
     * 获取{@link Student#weiNo}
     */
    @Column(name = "wei_no", length = 50)
    public String getWeiNo() {
        return weiNo;
    }

    /**
     * 设置{@link Student#weiNo}
     */
    public void setWeiNo(String weiNo) {
        this.weiNo = weiNo;
    }

    /**
     * 获取{@link Student#openId}
     */
    @Column(name = "openid", length = 50)
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置{@link Student#openId}
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取{@link Student#password}
     */
    @Column(name = "pswd", length = 50)
    public String getPassword() {
        return password;
    }

    /**
     * 设置{@link Student#password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
