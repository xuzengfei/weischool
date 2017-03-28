package com.ws.service.teacher;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.teacher.Teacher;
import com.ws.pojo.teacher.TeacherOpenId;
import com.ws.service.teacher.dao.TeacherOpenIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师登录服务接口
 * Created by admin on 2017/3/23.
 */
@Service
public class TeacherOpenIdServiceImpl implements TeacherOpenIdService {
    @Autowired
    private TeacherOpenIdDao dao;
    @Autowired
    private TeacherService teacherService;

    @Override
    public TeacherOpenId get(String openId) {
        if (openId == null)
            return null;
        return this.dao.findUniqueResult("from TeacherOpenId s where s.openId =? ", openId);
    }

    @Override
    public ResultCode save(String openId, String tcNo) {
        if (openId == null || tcNo == null || tcNo == null || "".equals(openId) || "".equals(tcNo) || "".equals(tcNo))
            return ResultCode.INVALID;
        Teacher teacher = this.teacherService.getByNo(tcNo);
        if (teacher == null)
            return ResultCode.INVALID;
        TeacherOpenId teacherOpenId = this.get(openId);
        if (teacherOpenId == null) {
            this.dao.add(new TeacherOpenId(teacher.getId(), openId, System.currentTimeMillis()));
        } else {
            this.dao.update(new TeacherOpenId(teacher.getId(), openId, null));
        }
        return ResultCode.SUCCESS;
    }
}
