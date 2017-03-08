package com.ws.service.exam;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.exam.Exam;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by admin on 2017/3/8.
 */
public interface ExamService {
    /**
     * 添加
     * @param exam 添加的对象值
     * @return ResultCode
     */
    ResultCode add(Exam exam);
    /**
     * 更新
     * @param exam 更新对象
     * @return ResultCode
     */
    ResultCode edit(Exam exam);

    /**
     * 获得对象
     * @param id 传入主键
     * @return  Exam值
     */
    Exam get(String id);
    /**
     * 分页查询
     *
     * @param exam  对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Exam exam, DataGrid<Exam> datagrid, HttpServletRequest request);


}
