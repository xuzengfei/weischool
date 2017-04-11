package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeReg;
import com.ws.pojo.grade.GradeRegEm;

import javax.servlet.http.HttpServletRequest;

/**
 * 上课签到服务层
 * Created by admin on 2017/4/11.
 */
public interface GradeRegService {
    /**
     * 添加
     *
     * @param gradeReg
     */
    void add(GradeReg gradeReg);

    /**
     * 物理删除
     *
     * @param id     需要的删除的ID
     * @param idType ID的类型 1--主键 2--学生ID
     */
    void del(String id, GradeRegEm idType);

    /**
     * 更新操作
     *
     * @param id     主键
     * @param status 状态
     * @return
     */
    ResultCode edit(String id, short status);

    /**
     * 分页查询
     *
     * @param gradeReg 班级对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(GradeReg gradeReg, DataGrid<GradeReg> datagrid, HttpServletRequest request);
}
