package com.ws.service.student;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.attach.AttachService;
import com.ws.service.student.dao.StudentDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
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
@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = Logger
            .getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentDao studentDao;
    //**附件接口
    @Autowired
    private AttachService attachService;
    //**班级成员服务接口
    @Autowired
    private StudentGradeService studentGradeService;

    @Override
    public Student get(String id) {
        if (id == null)
            return null;
        return studentDao.get(id);
    }

    @Override
    public ResultCode edit(Student student, boolean isExistValid) {

        if (student.getId() == null)
            return ResultCode.INVALID;
        if (isExistValid && student.getNo() != null) {
            Student r = this.get(student.getId());
            if (!student.getNo().equals(r.getNo())
                    && this.findByNo(student.getNo()) != null)
                return ResultCode.EXIST;
        }
        this.studentDao.update(student);
        if(student.getDelFlag()==1){

            studentGradeService.delByStId(student.getId());
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public Student findByNo(String no) {
        if (no == null)
            return null;
        return this.studentDao.findUniqueResult(
                " from Student r where r.no=? and r.delFlag=?", no, 0);
    }

    @Override
    public List<Student> find(Student student) {
        Criteria cq = this.studentDao.getSession().createCriteria(Student.class);
        if (student.getId() != null)
            cq.add(Expression.eq("id", student.getId()));
        if (student.getName() != null)
            cq.add(Expression.like("name", "%" + student.getName() + "%"));
        if (student.getIsenable() != null)
            cq.add(Expression.eq("isenable", student.getIsenable()));
        cq.add(Expression.eq("delFlag", 0));
        cq.addOrder(Order.desc("createTime"));
        return cq.list();
    }

    @Override
    public ResultCode add(Student student) {

        if (student.getName() == null)
            return ResultCode.INVALID;
        if (this.findByNo(student.getNo()) != null)
            return ResultCode.EXIST;
        this.studentDao.add(student);
        String[] pic = new String[1];
        if (student.getPic() != null) {
            pic[0] = student.getPic();
            this.attachService.edit(pic, student.getId());
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public void datagrid(Student student, DataGrid<Student> datagrid,
                         HttpServletRequest request) {
        List<String[]> orders = new ArrayList<String[]>();
        orders.add(new String[]{"createTime", "desc"});
        datagrid.setOrder(orders);
        this.studentDao.datagrid(student, datagrid, request);
    }

}
