package com.ws.service.student;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campus.Campus;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.student.dao.StudentOpenIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户微信端登录绑定信息服务接口实现
 * Created by admin on 2017/2/15.
 */
@Service(value = "studentOpenIdService")
public class StudentOpenIdServiceImpl implements StudentOpenIdService {
    @Autowired
    private StudentOpenIdDao dao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentGradeService studentGradeService;


    @Override
    public StudentOpenId get(String openId) {
        if (openId == null)
            return null;
        return this.dao.findUniqueResult("from StudentOpenId s where s.openId =? ", openId);
    }

    @Override
    public StudentOpenId getById(String id) {
        if (id == null)
            return null;
        return this.dao.get(id);
    }

    /**
     * 1.判断是否合法
     * 2.判断用户是否存在
     * 3.是否存在班级学员
     * 4.通过userId查询用户是否已经绑定过了，如果已经绑定了，则不能重复绑定
     * 5.如果通过userId没有绑定，那么检查openId有没有被使用了，如果openId已经被使用了，不能重复使用
     *
     * @param openId openId
     * @param stNo   学生学号
     * @param cpId   校区ID
     * @return
     */
    @Override
    public ResultCode save(String openId, String stNo, String cpId) {
        if (openId == null || stNo == null || cpId == null || "".equals(openId) || "".equals(stNo) || "".equals(cpId))
            return ResultCode.INVALID;
        Student student = this.studentService.findByNo(stNo);
        if (student == null)
            return ResultCode.INVALID;

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudent(student);
        studentGrade.setCampus(new Campus(cpId));
        studentGrade.setIsenable(1);
        List<StudentGrade> list = studentGradeService.find(studentGrade);
        if (list == null || list.isEmpty())
            return ResultCode.INVALID;
        StudentOpenId studentOpenId = this.dao.findUniqueResult("from StudentOpenId s where s.stId =? ", student.getId());
        ;
        if (studentOpenId == null) {
            if (this.get(openId) == null)
                this.dao.add(new StudentOpenId(student.getId(), openId, cpId, System.currentTimeMillis()));
            else
                return ResultCode.EXIST;
        } else {
            return ResultCode.EXIST;
            //   if(studentOpenId.getCpId())
           /* studentOpenId.setCpId(cpId);
            studentOpenId.setCt(null);
            studentOpenId.setOpenId(null);
            studentOpenId.setStId(null);
            this.dao.update(studentOpenId);*/
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public int save(String openId, String stId, String cpId, Long ct) {
        StudentOpenId studentOpenId = this.dao.findUniqueResult("from StudentOpenId s where s.stId =? and s.openId =? ", stId, openId);
        if (studentOpenId != null)
            return -1;
        this.dao.add(new StudentOpenId(stId, openId, cpId, ct));
        return 1;
    }

    @Override
    public List<StudentOpenId> getListByOpenId(String openId) {
        if (openId == null)
            return null;
        return this.dao.find("from StudentOpenId s where s.openId =? ", openId);
    }

}
