package com.ws.pojo.teacher;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by admin on 2017/2/15.
 */
@Entity
@Table(name = "ws_tc_openid")
public class TeacherOpenId {
    private String id;
    private String tcId;
    private String openId;
    private Long ct;

    public TeacherOpenId() {
    }

    public TeacherOpenId(String tcId, String openId, String cpId, Long ct) {
        this.tcId = tcId;
        this.openId = openId;
        this.ct = ct;
    }

    @Override
    public String toString() {
        return "TeacherOpenId{" +
                "id='" + id + '\'' +
                ", tcId='" + tcId + '\'' +
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
    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }

    @Column(name = "open_id", length = 36)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "ct")
    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }
}
