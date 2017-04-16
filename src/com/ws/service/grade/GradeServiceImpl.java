package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.Grade;
import com.ws.service.grade.dao.GradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Override
    public Grade get(String id) {
        if (id == null)
            return null;
        return this.gradeDao.get(id);
    }

    @Override
    public void datagrid(Grade grade, DataGrid<Grade> datagrid,
                         HttpServletRequest request) {
        List<String[]> orders = new ArrayList<String[]>();
        // orders.add(new String[]{"orderby","asc"});
        orders.add(new String[]{"createTime", "desc"});
        datagrid.setOrder(orders);
        this.gradeDao.datagrid(grade, datagrid, request);

    }

    @Override
    public ResultCode add(Grade grade) {

        if (grade.getName() == null || grade.getCampus().getId() == null
                || "".equals(grade.getCampus().getId()))
            return ResultCode.INVALID;
        if (!this.findByName(grade.getName(), grade.getCampus().getId())
                .isEmpty())
            return ResultCode.EXIST;
        this.gradeDao.add(grade);
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode update(Grade grade, boolean isNameExistValid) {

        if (grade.getId() == null)
            return ResultCode.INVALID;
        if (isNameExistValid && grade.getName() != null) {
            Grade r = this.get(grade.getId());
            if (!grade.getName().equals(r.getName())
                    && !this.findByName(grade.getName(),
                    grade.getCampus().getId()).isEmpty())
                return ResultCode.EXIST;
        }
        this.gradeDao.update(grade);
        return ResultCode.SUCCESS;
    }

    @Override
    public List<Grade> find(Grade grade) {
        return this.gradeDao.find(grade);
    }

    public List<Grade> findByName(String name, String cpId) {
        return this.gradeDao
                .find(" from Grade r where r.name=? and r.campus.id=?   and r.delFlag=?",
                        name, cpId, 0);
    }
}
