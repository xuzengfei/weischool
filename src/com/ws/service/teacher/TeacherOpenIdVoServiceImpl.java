package com.ws.service.teacher;

import com.bugframework.common.pojo.DataGrid;
import com.ws.pojo.teacher.TeacherOpenIdVo;
import com.ws.service.teacher.dao.TeacherOpenIdVoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 许增飞
 * @description 老师微信绑定服务接口
 * @datetime 2017/6/12 14:27.
 */
@Service
public class TeacherOpenIdVoServiceImpl implements TeacherOpenIdVoService {
    @Autowired
    private TeacherOpenIdVoDao dao;

    @Override
    public void datagrid(TeacherOpenIdVo teacherOpenIdVo, DataGrid<TeacherOpenIdVo> datagrid, HttpServletRequest request) {
        this.dao.datagrid(teacherOpenIdVo, datagrid, request);
    }

    @Override
    public void del(String id) {
        this.dao.delete(id);
    }

}
