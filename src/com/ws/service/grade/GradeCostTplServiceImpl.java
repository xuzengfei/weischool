package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeCostTpl;
import com.ws.service.grade.dao.GradeCostTplDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by admin on 2017/3/17.
 */
@Service
public class GradeCostTplServiceImpl implements GradeCostTplService {
    @Autowired
    private GradeCostTplDao dao;

    @Override
    public ResultCode add(GradeCostTpl t) {
        this.dao.add(t);
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode edit(GradeCostTpl t) {
        if (t.getId() == null)
            return ResultCode.INVALID;
        this.dao.update(t);
        return ResultCode.SUCCESS;
    }

    @Override
    public void datagrid(GradeCostTpl g, DataGrid<GradeCostTpl> datagrid, HttpServletRequest request) {
        this.dao.datagrid(g, datagrid, request);
    }

    @Override
    public GradeCostTpl get(String id) {
        if (id == null)
            return null;
        return this.dao.get(id);
    }

    @Override
    public ResultCode del(String id) {
        if (id == null)
            return ResultCode.INVALID;
        this.dao.delete(id);
        return ResultCode.SUCCESS;
    }

    @Override
    public List<GradeCostTpl> find(String gradeId) {
        if (gradeId == null)
            return null;
        return this.dao.find("from GradeCostTpl g where g.gradeId =? order by g.createTime desc ", gradeId);
    }
}
