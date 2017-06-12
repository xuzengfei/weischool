package com.ws.service.student;

import com.bugframework.common.pojo.DataGrid;
import com.ws.pojo.student.StudentOpenIdVo;
import com.ws.service.student.dao.StudentOpenIdVoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 许增飞
 * @description 学生微信绑定服务接口
 * @datetime 2017/6/12 14:27.
 */
@Service
public class StudentOpenIdVoServiceImpl implements StudentOpenIdVoService {
    @Autowired
    private StudentOpenIdVoDao dao;

    @Override
    public void datagrid(StudentOpenIdVo studentOpenIdVo, DataGrid<StudentOpenIdVo> datagrid, HttpServletRequest request) {
        this.dao.datagrid(studentOpenIdVo, datagrid, request);
    }

    @Override
    public void del(String id) {
        this.dao.delete(id);
    }

}
