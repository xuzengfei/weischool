package com.ws.service.student;

import com.bugframework.common.pojo.DataGrid;
import com.ws.pojo.student.StudentOpenIdVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 许增飞
 * @description 学生微信绑定服务接口
 * @datetime 2017/6/12 14:27.
 */
public interface StudentOpenIdVoService {
    /**
     * 分页查询
     *
     * @param studentOpenIdVo 学生微信绑定对象值
     * @param datagrid        分页模型
     * @param request         HttpServletRequest 请求
     */
    void datagrid(StudentOpenIdVo studentOpenIdVo, DataGrid<StudentOpenIdVo> datagrid, HttpServletRequest request);

    /**
     * 解除绑定
     *
     * @param id
     */
    void del(String id);

}
