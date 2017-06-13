package com.ws.service.student;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.student.dao.StudentGradeDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service(value = "studentGradeService")
public class StudentGradeServiceImpl implements StudentGradeService {

    @Autowired
    private StudentGradeDao studentGradeDao;
    @Autowired
    StudentService studentService;

    @Override
    public StudentGrade get(String id) {
        if (id == null)
            return null;
        return this.studentGradeDao.get(id);
    }

    @Override
    public ResultCode edit(StudentGrade studentGrade, boolean isExistValid) {

        if (studentGrade.getId() == null)
            return ResultCode.INVALID;
        if (isExistValid && studentGrade.getStudent() != null
                && studentGrade.getStudent().getId() != null
                && !"".equals(studentGrade.getStudent().getId())
                && studentGrade.getCampus() != null
                && studentGrade.getCampus().getId() != null
                && !"".equals(studentGrade.getCampus().getId())
                && studentGrade.getGrade() != null
                && studentGrade.getGrade().getId() != null
                && !"".equals(studentGrade.getGrade().getId())) {
            StudentGrade r = this.get(studentGrade.getId());
            if (!studentGrade.getStudent().getId()
                    .equals(r.getStudent().getId())
                    && !studentGrade.getCampus().getId()
                    .equals(r.getCampus().getId())
                    && !studentGrade.getGrade().getId()
                    .equals(r.getGrade().getId())
                    && !this.isExist(studentGrade.getStudent().getId(),
                    studentGrade.getGrade().getId(),
                    studentGrade.getCampus().getId()).isEmpty())
                return ResultCode.EXIST;
        }
        this.studentGradeDao.update(studentGrade);

        return ResultCode.SUCCESS;
    }

    private List<Object> isExist(String studentId, String graId, String cpId) {
        return studentGradeDao
                .findObject(
                        "select r.id from StudentGrade r where r.student.id=? and r.grade.id=? and r.campus.id=?   and r.delFlag=?",
                        studentId, graId, cpId, 0);
    }

    @Override
    public ResultCode add(StudentGrade studentGrade) {

        if (studentGrade.getStudent() == null
                || studentGrade.getStudent().getNo() == null
                || "".equals(studentGrade.getStudent().getNo()))
            return ResultCode.INVALID;
        Student student = studentService.findByNo(studentGrade.getStudent()
                .getNo());
        if (student == null)
            return ResultCode.NOEXIST;
        studentGrade.setStudent(student);
        if (studentGrade.getStudent().getId() == null
                || "".equals(studentGrade.getStudent().getId())
                || studentGrade.getCampus() == null
                || studentGrade.getCampus().getId() == null
                || "".equals(studentGrade.getCampus().getId())
                || studentGrade.getGrade() == null
                || studentGrade.getGrade().getId() == null
                || "".equals(studentGrade.getGrade().getId()))
            return ResultCode.INVALID;
        if (!this.isExist(studentGrade.getStudent().getId(),
                studentGrade.getGrade().getId(),
                studentGrade.getCampus().getId()).isEmpty())
            return ResultCode.EXIST;
        this.studentGradeDao.add(studentGrade);

        return ResultCode.SUCCESS;
    }

    @Override
    public void datagrid(StudentGrade studentGrade,
                         DataGrid<StudentGrade> datagrid, HttpServletRequest request) {
        this.studentGradeDao.datagrid(studentGrade, datagrid, request);

    }

    @Override
    public List<StudentGrade> find(StudentGrade sg) {
        Criteria cq = this.studentGradeDao.getSession().createCriteria(StudentGrade.class);
        if (sg.getCpId() != null) {
            cq.createAlias("campus", "cq");
            cq.add(Expression.eq("cq.id", sg.getCpId()));
        }
        if (sg.getGradeId() != null) {
            cq.createAlias("grade", "g");
            cq.add(Expression.eq("g.id", sg.getGradeId()));
        }
        if (sg.getIsenable() != null) {
            cq.add(Expression.eq("isenable", sg.getIsenable()));
        }
        if (sg.getStudent() != null && sg.getStudent().getId() != null) {
            cq.createAlias("student", "st");
            cq.add(Expression.eq("st.id", sg.getStudent().getId()));
        }
        cq.add(Expression.eq("delFlag",0));
        cq.addOrder(Order.desc("createTime"));
        return cq.list();
    }

    @Override
    public List<StudentGrade> findOrderRestClass(String cpId, String stId) {
        if (cpId == null || stId == null)
            return null;
        Criteria cq = this.studentGradeDao.getSession().createCriteria(StudentGrade.class);
        cq.createAlias("campus", "cq");
        cq.add(Expression.eq("cq.id", cpId));
        cq.createAlias("student", "st");
        cq.add(Expression.eq("st.id", stId));
        cq.add(Expression.eq("isenable", 1));
        cq.add(Expression.eq("delFlag",0));
        cq.addOrder(Order.asc("restClass"));
        return cq.list();
    }

    @Override
    public List<StudentGrade> findByGrade(String gradeId) {
        if (gradeId == null)
            return null;
        Criteria cq = this.studentGradeDao.getSession().createCriteria(StudentGrade.class);
        cq.createAlias("grade", "g");
        cq.add(Expression.eq("g.id", gradeId));
        cq.add(Expression.eq("delFlag",0));
        return cq.list();
    }

    @Override
    public void updateRestClass(String id, String  rest) {
        this.studentGradeDao.batchExecute("update StudentGrade s set s.restClass =s.restClass"+rest+"2 where id=?",id);
    }

    @Override
    public void delByStId(String id) {
        this.studentGradeDao.batchExecute("update StudentGrade s set s.delFlag = 1 where s.student.id=?",id);
    }

    @Override
    public List<StudentGrade> findByStId(String s) {
        Criteria cq = this.studentGradeDao.getSession().createCriteria(StudentGrade.class);
        cq.createAlias("student", "st");
        cq.add(Expression.eq("st.id",s));
        return cq.list();
    }

}
