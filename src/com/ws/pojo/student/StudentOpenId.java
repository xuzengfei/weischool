package com.ws.pojo.student;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 学生微信绑定
 * Created by admin on 2017/2/15.
 */
@Entity
@Table(name = "ws_st_openid")
public class StudentOpenId {
    /**
     *主键
     */
    private String id;
    /**
     * 学生ID
     */
    private String stId;
    /**
     * 微信用户在微信公众号唯一标识
     */
    private String openId;
    /**
     * 校区ID
     */
    private String cpId;
    /**
     * 创建时间
     */
    private Long ct;
    /**
     * 学生名称
     */
    private String stName;

    public StudentOpenId() {
    }

    public StudentOpenId(String stId, String openId, String cpId, Long ct) {
        this.stId = stId;
        this.openId = openId;
        this.cpId = cpId;
        this.ct = ct;
    }

    @Override
    public String toString() {
        return "StudentOpenId{" +
                "id='" + id + '\'' +
                ", stId='" + stId + '\'' +
                ", openId='" + openId + '\'' +
                ", ct=" + ct +
                '}';
    }

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(name = "id", length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "st_id", length = 36)
    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    @Column(name = "open_id", length = 36)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "cp_id", length = 36)
    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    @Column(name = "ct")
    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }
    @Transient
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }
}
