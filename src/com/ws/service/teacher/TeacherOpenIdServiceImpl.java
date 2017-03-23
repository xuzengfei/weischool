package com.ws.service.teacher;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.teacher.TeacherOpenId;
import com.ws.service.teacher.dao.TeacherOpenIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/3/23.
 */
@Service
public class TeacherOpenIdServiceImpl implements TeacherOpenIdService {
    @Autowired
    private TeacherOpenIdDao dao;
    @Override
    public TeacherOpenId get(String openId) {
        if (openId==null)
        return null;
        return this.dao.findUniqueResult("from TeacherOpenId s where s.openId =? ", openId);
    }

    @Override
    public ResultCode save(String openId, String tcNo) {
        if (openId == null || tcNo == null || cpId == null|| "".equals(openId)|| "".equals(tcNo)|| "".equals(cpId))
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
