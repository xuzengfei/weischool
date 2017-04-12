package com.ws.controller.wei.tc;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.DateUtils;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.controller.wei.tc.common.WeiTcLoginUtils;
import com.ws.pojo.attach.Attach;
import com.ws.pojo.grade.Grade;
import com.ws.pojo.grade.GradeReg;
import com.ws.pojo.grade.GradeTime;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.teacher.Teacher;
import com.ws.pojo.teacher.TeacherOpenId;
import com.ws.service.attach.AttachService;
import com.ws.service.grade.GradeRegService;
import com.ws.service.grade.GradeService;
import com.ws.service.grade.GradeTimeService;
import com.ws.service.student.StudentGradeService;
import com.ws.service.teacher.TeacherService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private GradeRegService gradeRegService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
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
                    gradMap.put("gtId", gradeTime.getId());
                    gradMap.put("id", g.getId());
                    gradMap.put("name", g.getName());
                    gradMap.put("st", gradeTime.getStart());
                    gradMap.put("et", gradeTime.getEnd());
                    if (System.currentTimeMillis() < gradeTime.getStart())
                        gradMap.put("status", 0);
                    else if (System.currentTimeMillis() >= gradeTime.getStart() && System.currentTimeMillis() < gradeTime.getEnd())
                        gradMap.put("status", 1);
                    else
                        gradMap.put("status", 2);
                    if (gradeDatas.containsKey(g.getCampus().getName())) {
                        List<Map<String, Object>> gradMap1 = (List<Map<String, Object>>) gradeDatas.get(g.getCampus().getName());
                        gradMap1.add(gradMap);
                    } else {
                        List<Map<String, Object>> list = new ArrayList<>();
                        list.add(gradMap);
                        gradeDatas.put(g.getCampus().getName(), list);
                    }
                }
            }
        }
        datas.put("week", 1 - DateUtils.getMondayPlus());
        datas.put("date", DateUtils.getNowDay());
        datas.put("tcName", teacher.getName());
        datas.put("gradeDatas", gradeDatas);
        datas.put("emNo", teacher.getEmNo());
        return new AjaxJson(null, true, datas);
    }

    /**
     * 进入签到界面
     *
     * @param gradeId 班级ID
     * @param gtId    班次ID
     * @return
     */
    @RequestMapping(value = "/callname/grade/{gradeId}/gradeTime/{gtId}", method = RequestMethod.GET)
    public ModelAndView toCallName(@PathVariable String gradeId, @PathVariable String gtId,String gradeName) {
        return new ModelAndView("/wei/tc/callName", "gradeId", gradeId).addObject("gtId", gtId).addObject("gradeName",gradeName);
    }

    /**
     * 获得班级所有的用户
     *
     * @param gradeId 班级ID
     * @return
     */
    @RequestMapping(value = "/grade/{gradeId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getUser(@PathVariable String gradeId) {
        List<StudentGrade> studentGrades = studentGradeService.findByGrade(gradeId);
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> reMap = null;
        Attach attach = null;
        List<Attach> list = null;
        for (StudentGrade s : studentGrades) {
            reMap = new HashedMap();
            reMap.put("id", s.getId());
            reMap.put("stName", s.getStudentName());

            attach = new Attach();
            attach.setModuleId(WeiStLoginUtils.getStudentSession().getStId());
            list = this.attachService.list(attach);
            if (list != null && !list.isEmpty()) {
                reMap.put("pic", list.get(0).getPath());
            } else {
                reMap.put("pic", "");
            }
            results.add(reMap);
        }
        return new AjaxJson(null, true, results);
    }

    /**
     * 获得签到的用户
     *
     * @param gradeId
     * @return
     */
    @RequestMapping(value = "/grade/reg/{gtId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getRegUser(@PathVariable String gradeId) {
        List<GradeReg> gradeRegs = gradeRegService.find(gradeId);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        for (GradeReg g : gradeRegs) {
            map = new HashedMap();
            map.put("id", g.getId());
            map.put("status", g.getStatus());
            map.put("stId", g.getStId());
            list.add(map);
        }

        return new AjaxJson(null, true, list);
    }


}
