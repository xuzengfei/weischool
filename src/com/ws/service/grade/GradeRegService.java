package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeReg;
import com.ws.pojo.grade.GradeRegEm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 上课签到服务层
 * Created by admin on 2017/4/11.
 */
public interface GradeRegService {
    /**
     * 添加
     * <p>添加的时候一定要将该学生班级课程的数量进行处理。处理如下：</p>
     * <p>1.判断是否点名状态（准时、请假、旷课），如果是正常和旷课那么，就要讲剩余课程减一。</p>
     *
     * @param gradeReg 点名的数据bean
     * @param sgId     班级学员表ID
     */
    void add(GradeReg gradeReg, String sgId);

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
     * 回滚原来状态，课时也要还原
     *
     * @param id   主键
     * @param sgId 班级学员表ID
     */
    void rollBack(String id, String sgId);
    /**
     * 更新操作
     * <p>添加的时候一定要将该学生班级课程的数量进行处理。处理如下：</p>
     *  <p>1.获取原来的状态</p>
     *  <p>2.原来的状态和当前状态比较，有几种可能：2.1原来状态是准或旷变成旷或准，则不需要处理；2.2原来状态是准或旷变成请，则剩余课时+1；2.3原来状态是请变成准或旷，则剩余课时-1；2.4原来状态是请变成请，则不需要处理.</p>
     *
     * @param id     主键
     * @param status 状态
     *  @param sgId 班级学员表ID
     * @return
     *//*
    ResultCode edit(String id, short status,String sgId);*/

    /**
     * 分页查询
     *
     * @param gradeReg 班级对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(GradeReg gradeReg, DataGrid<GradeReg> datagrid, HttpServletRequest request);

    /**
     * 获得班次下所有的学生签到信息
     *
     * @param gtId
     * @return
     */
    List<GradeReg> find(String gtId);
}
