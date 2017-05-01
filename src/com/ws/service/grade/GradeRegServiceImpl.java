package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeReg;
import com.ws.pojo.grade.GradeRegEm;
import com.ws.service.grade.dao.GradeRegDao;
import com.ws.service.student.StudentGradeService;
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
    @Autowired
    private StudentGradeService studentGradeService;

    @Override
    public void add(GradeReg gradeReg, String sgId) {
        studentGradeService.updateRestClass(sgId, "-");
        this.dao.add(gradeReg);
    }

    /* @Override
     public void add(GradeReg gradeReg, String sgId) {
         if (gradeReg.getStatus() == 1 || gradeReg.getStatus() == 3)
             studentGradeService.updateRestClass(sgId, "-");
         this.dao.add(gradeReg);
     }*/
    @Override
    public void del(String id, GradeRegEm idType) {
        this.dao.delete(idType.getVal(), id);
    }

    /**
     * @param id     主键
     * @param status 状态
     * @return
     */
    @Override
    public ResultCode edit(String id, short status) {
        this.dao.batchExecute("update GradeReg g set g.status =? where id = ?", status, id);
        return ResultCode.SUCCESS;
    }/*
    */

    /**
     * 2.1原来状态是准或旷变成旷或准，则不需要处理；2.2原来状态是准或旷变成请，则剩余课时+1；2.3原来状态是请变成准或旷，则剩余课时-1；2.4原来状态是请变成请，则不需要处理
     *
     * @param id     主键
     * @param status 状态
     * @return
     *//*
    @Override
    public ResultCode edit(String id, short status, String sgId) {
        GradeReg gradeReg = this.dao.get(id);
        int olstatus = gradeReg.getStatus();
        if (olstatus == 1 || olstatus == 3) {
            if (status == 2) {
                studentGradeService.updateRestClass(sgId, "+");
            }
        } else {
            if (status == 1 || status == 3) {
                studentGradeService.updateRestClass(sgId, "-");
            }
        }
        this.dao.batchExecute("update GradeReg g set g.status =? where id = ?", status, id);
        return ResultCode.SUCCESS;
    }*/
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
