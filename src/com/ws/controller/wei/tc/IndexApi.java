package com.ws.controller.wei.tc;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.DateUtils;
import com.ws.controller.wei.tc.common.WeiTcLoginUtils;
import com.ws.pojo.grade.Grade;
import com.ws.pojo.grade.GradeTime;
import com.ws.pojo.teacher.Teacher;
import com.ws.pojo.teacher.TeacherOpenId;
import com.ws.service.grade.GradeService;
import com.ws.service.grade.GradeTimeService;
import com.ws.service.teacher.TeacherService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/28.
 */
@Controller
@RequestMapping("/wei/tc")
public class IndexApi {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeTimeService gradeTimeService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public AjaxJson getData() {
        Map<String, Object> datas = new HashedMap();
        TeacherOpenId teacherOpenId = WeiTcLoginUtils.getTeacherSession();
        Teacher teacher = this.teacherService.get(teacherOpenId.getTcId());
        Grade grade = new Grade();
        grade.setIsenable(1);
        grade.setTeacher(teacher.getId());
        List<Grade> gradeList = gradeService.find(grade);
        Map<String, Object> gradeDatas = new HashedMap();
        for (Grade g : gradeList) {
            List<GradeTime> gradeTimes = gradeTimeService.list(g.getId(), DateUtils.getStartTime(), DateUtils.getEndTime());
            if (gradeTimes != null && gradeTimes.size() > 0) {//只显示当天的课程
                for (GradeTime gradeTime : gradeTimes) {//status 为0-课程未开始 1--课程进行中 2--课程结束
                    Map<String, Object> gradMap = new HashedMap();
                    gradMap.put("id", g.getId());
                    gradMap.put("name", g.getName());
                    if (System.currentTimeMillis() < gradeTime.getStart())
                        gradMap.put("status", 0);
                    else if (System.currentTimeMillis() >= gradeTime.getStart() && System.currentTimeMillis() < gradeTime.getEnd())
                        gradMap.put("status", 1);
                    else
                        gradMap.put("status", 2);
                    if (gradeDatas.containsKey(g.getCampus().getName())) {
                        List<Map<String, Object>> gradMap1 = (List<Map<String, Object>>) gradeDatas.get(g.getCpId());
                        gradMap1.add(gradMap);
                    } else {
                        List<Map<String, Object>> list = new ArrayList<>();
                        list.add(gradMap);
                        gradeDatas.put(g.getCampus().getName(), list);
                    }
                }
            }
        }
        datas.put("week", DateUtils.getMondayPlus());
        datas.put("date", DateUtils.getNowDay());
        datas.put("tcName", teacher.getName());
        datas.put("gradeDatas", gradeDatas);
        datas.put("emNo",teacher.getEmNo());
        return new AjaxJson(null, true, datas);
    }
}
