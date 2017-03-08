package com.ws.util;

/**
 * 模块类型
 * Created by 许增飞 on 2017/1/10.
 */
public enum ModuleType {
    /**
     * 学生模块
     */
    STUDENT("student"),
    /**
     * 荣誉证书模块
     */
    CERTIFICATE("certificate"),
    /**
     * 成长经历模块
     */
    EXPERIENTCE("experience");

    private String value;
    private ModuleType(String value){

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




    @Override
    public String toString() {
        return  this.getValue();
    }
}
