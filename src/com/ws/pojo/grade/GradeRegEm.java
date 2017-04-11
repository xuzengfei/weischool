package com.ws.pojo.grade;

/**
 * Created by admin on 2017/4/11.
 */
public enum GradeRegEm {
    /**
     * 主键
     */
    ID("id"),
    /**
     * 课程ID
     */
    GRADEID("gradeId"),
    /**
     * 学生ID
     */
    STUDENTID("stId"),
    /**
     * 班次ID
     */
    GTID("gtId");
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    private GradeRegEm(String val) {
        this.val = val;
    }
}
