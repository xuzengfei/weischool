package com.ws.service.exam;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.exam.Exam;
import com.ws.service.exam.dao.ExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/8.
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;

    @Override
    public ResultCode add(Exam exam) {
        if (exam.getStudent() == null || exam.getStudent().getId() == null || "".equals(exam.getStudent().getId()))
            return ResultCode.INVALID;
        examDao.add(exam);
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode edit(Exam exam) {
        if (exam.getId() == null)
            return ResultCode.INVALID;
        this.examDao.update(exam);
        return ResultCode.SUCCESS;
    }

    @Override
    public Exam get(String id) {
        if (id == null)
            return null;
        return this.examDao.get(id);
    }

    @Override
    public void datagrid(Exam exam, DataGrid<Exam> datagrid, HttpServletRequest request) {
        List<String[]> orders = new ArrayList<String[]>();
        orders.add(new String[]{"exTime", "desc"});
        datagrid.setOrder(orders);
        this.examDao.datagrid(exam, datagrid, request);
    }
}
