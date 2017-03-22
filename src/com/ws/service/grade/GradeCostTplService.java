package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeCostTpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 班级费用模板服务接口
 */
public interface GradeCostTplService {
    /**
     * 添加操作
     *
     * @param t 对象
     * @return ResultCode
     */
    ResultCode add(GradeCostTpl t);

    /**
     * g更新
     *
     * @param t 对象
     * @return ResultCode
     */
    ResultCode edit(GradeCostTpl t);

    /**
     * 分页查询
     *
     * @param g        GradeCostTpl对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(GradeCostTpl g, DataGrid<GradeCostTpl> datagrid, HttpServletRequest request);

    /**
     * 通过主键获取班级
     *
     * @param id 班级ID
     * @return
     */
    GradeCostTpl get(String id);

    /**
     * 通过ID删除
     *
     * @param id 主键
     * @return
     */
    ResultCode del(String id);

    /**
     * 获得列表
     *
     * @param gradeId 查询班级
     * @return
     */
    List<GradeCostTpl> find(String gradeId);

}
