package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeReg;
import com.ws.pojo.grade.GradeRegEm;
import com.ws.service.grade.dao.GradeRegDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程签到
 * Created by admin on 2017/4/11.
 */
@Service
public class GradeRegServiceImpl implements GradeRegService {
    @Autowired
    private GradeRegDao dao;

    @Override
    public void add(GradeReg gradeReg) {
        this.dao.add(gradeReg);
    }

    @Override
    public void del(String id, GradeRegEm idType) {
        this.dao.delete(idType.getVal(), id);
    }

    @Override
    public ResultCode edit(String id, short status) {
        this.dao.batchExecute("update GradeReg g set g.status =? where id = ?", status, id);
        return ResultCode.SUCCESS;
    }

    @Override
    public void datagrid(GradeReg gradeReg, DataGrid<GradeReg> datagrid, HttpServletRequest request) {
        this.dao.datagrid(gradeReg, datagrid, request);
    }

    @Override
    public List<GradeReg> find(String gtId) {
        return this.dao.find("from GradeReg g where g.gtId =? ", gtId);
    }

    private boolean isExist(String gtId, String stId) {
        List<GradeReg> list = this.dao.find("from GradeReg g where gtId=? and stId=?", gtId, stId);
        return list == null || list.isEmpty() ? true : false;
    }
}
