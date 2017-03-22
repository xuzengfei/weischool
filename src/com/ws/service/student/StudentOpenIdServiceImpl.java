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
    public ResultCode save(String openId, String stNo, String cpId) {
        if (openId == null || stNo == null || cpId == null|| "".equals(openId)|| "".equals(stNo)|| "".equals(cpId))
            return ResultCode.INVALID;
        Student student = this.studentService.findByNo(stNo);
        if (student == null)
            return ResultCode.INVALID;

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudent(student);
        studentGrade.setCampus( new Campus(cpId));
        studentGrade.setIsenable(1);
        List<StudentGrade> list = studentGradeService.find(studentGrade);
        if (list == null || list.isEmpty())
            return ResultCode.INVALID;
        StudentOpenId studentOpenId  =this.get(openId);
        if(studentOpenId==null){
            this.dao.add(new StudentOpenId(student.getId(), openId, cpId, System.currentTimeMillis()));
        }else {
            studentOpenId.setCpId(cpId);
            studentOpenId.setCt(null);
            studentOpenId.setOpenId(null);
            studentOpenId.setStId(null);
            this.dao.update(new StudentOpenId());
        }
        return ResultCode.SUCCESS;
    }

}
