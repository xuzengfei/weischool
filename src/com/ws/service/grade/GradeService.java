package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.Grade;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
public interface GradeService {
    /**
     * 通过主键获取班级
     *
     * @param id 班级ID
     * @return
     */
    Grade get(String id);

    /**
     * 分页查询
     *
     * @param grade   班级对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Grade grade, DataGrid<Grade> datagrid, HttpServletRequest request);

    /**
     * 添加操作
     *
     * @param grade 班级对象
     * @return ResultCode
     */
    ResultCode add(Grade grade);

    /**
     * 更新
     *
     * @param grade           班级值，ID不能为空
     * @param isNameExistValid 是否班级名称是否存在验证
     * @return ResultCode
     */
    ResultCode update(Grade grade, boolean isNameExistValid);

    /**
     * 获得列表
     *
     * @param Grade 传入查询对象
     * @return
     */
    List<Grade> find(Grade Grade);
}
