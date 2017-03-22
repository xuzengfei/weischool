package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.ws.pojo.grade.ClassRegister;
import com.ws.service.grade.dao.ClassRegisterDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/1.
 */
@Service
public class ClassRegisterServiceImpl implements ClassRegisterService {
    @Autowired
    private ClassRegisterDao dao;

    @Override
    public void datagrid(ClassRegister classRegister, DataGrid<ClassRegister> datagrid, HttpServletRequest request) {
        this.dao.datagrid(classRegister, datagrid, request);
    }

    @Override
    public int getNormalNum(String gradeId, String stId) {
            if (gradeId==null||stId ==null)
                return 0;
        return  this.dao.countHqlResult("select count(id) from ClassRegister where gradeId =? and stId =? ",gradeId,stId);

    }


}
