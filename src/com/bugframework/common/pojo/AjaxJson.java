package com.bugframework.common.pojo;

import com.bugframework.common.utility.ResultCode;

import java.util.HashMap;
import java.util.Map;

public class AjaxJson {

    private String msg = "操作失败！";
    private boolean success = false;
    private Object obj;
    private Map<String, Object> attributes = new HashMap<String, Object>();

    public AjaxJson() {

    }

    public AjaxJson(String msg, boolean success, Object obj) {
        this.msg = msg;
        this.success = success;
        this.obj = obj;
    }

    public void setData(String msg, boolean success, Object obj) {
        this.msg = msg;
        this.success = success;
        this.obj = obj;
    }
    /*public AjaxJson(String msg, boolean success, Map<String, Object> attributes) {
		this.msg = msg;
		this.success = success;
		this.attributes = attributes;
	}*/


    public AjaxJson(String msg, boolean success, Object obj,
                    Map<String, Object> attributes) {
        this.msg = msg;
        this.success = success;
        this.obj = obj;
        this.attributes = attributes;
    }

    public AjaxJson result(ResultCode code) {

        switch (code) {
            case EXCEPTION:
                setData("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                setData("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            case EXIST:
                setData("已经存在", false, ResultCode.EXIST.getValue());
                break;
            default:
                setData("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


}
