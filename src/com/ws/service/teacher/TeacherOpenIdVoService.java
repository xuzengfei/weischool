package com.ws.service.teacher;

import com.bugframework.common.pojo.DataGrid;
import com.ws.pojo.teacher.TeacherOpenIdVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 许增飞
 * @description 老师微信绑定服务接口
 * @datetime 2017/6/12 14:27.
 */
public interface TeacherOpenIdVoService {
    /**
     * 分页查询
     *
     * @param teacherOpenIdVo 老师微信绑定对象值
     * @param datagrid        分页模型
     * @param request         HttpServletRequest 请求
     */
    void datagrid(TeacherOpenIdVo teacherOpenIdVo, DataGrid<TeacherOpenIdVo> datagrid, HttpServletRequest request);

    /**
     * 解除绑定
     *
     * @param id
     */
    void del(String id);
}
