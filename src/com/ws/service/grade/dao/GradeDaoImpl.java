package com.ws.service.grade.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.grade.Grade;
import javassist.compiler.ast.Expr;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Repository(value ="gradeDao")
public class GradeDaoImpl extends BaseDaoImpl<Grade> implements GradeDao {
    @Override
    public List<Grade> find(Grade grade) {
        Criteria cq = this.getCriteria();
        if(grade.getCampus()!=null){
            cq.createAlias("campus","cp");
            if(grade.getCampus().getId()!=null)
                cq.add(Expression.eq("cp.id", grade.getCampus().getId()));
            if(grade.getCampus().getIsenable()!=null)
                cq.add(Expression.eq("cp.isenable", grade.getCampus().getIsenable()));
        }
        if(grade.getTeacher()!=null){
            cq.add(Expression.eq("teacher",grade.getTeacher()));
        }
        if(grade.getIsenable()!=null){
            cq.add(Expression.eq("isenable",1));
        }
        cq.add(Expression.eq("delFlag",0));
        return cq.list();
    }
}
