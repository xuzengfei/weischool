package com.ws.controller.wei.st.controller.course;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.DateUtils;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.grade.GradeTime;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.grade.GradeTimeService;
import com.ws.service.student.StudentGradeService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/16.
 */
@Controller
@RequestMapping("/wei/st/timetable")
public class TimetableApi {
    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private GradeTimeService gradeTimeService;

    @RequestMapping(value = "/{week}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson get(@PathVariable Integer week){
        AjaxJson j = new AjaxJson();
       String[] weekDays = DateUtils.getWeekDays(week);
       if (weekDays!=null){
           Map<String,Object> result = new HashedMap();
           result.put("month",Integer.parseInt(weekDays[0].split("-")[1]));
           result.put("date",weekDays);
           String start = weekDays[0]+" 00:00:00";
           String end = weekDays[6]+" 23:59:59";
           List<StudentGrade> list = this.studentGradeService.findOrderRestClass(WeiStLoginUtils.getStudentSession().getCpId(), WeiStLoginUtils.getStudentSession().getStId());
           if(list!=null&&!list.isEmpty()){
               String[] gradIdArray= new String[list.size()];
               for (int i=0;i<list.size();i++){
                   gradIdArray[i] =list.get(i).getGradeId();
               }
               List<GradeTime> gradeTimes = gradeTimeService.list(gradIdArray,DateUtils.strTotimstrap(start),DateUtils.strTotimstrap(end));
               for (GradeTime g:gradeTimes){
                   for (StudentGrade sg:list){
                       if (g.getGradId().equals(sg.getGradeId())){
                           g.setGradName(sg.getGrade().getName());
                           break;
                       }
                   }
               }
               result.put("data",gradeTimes);
           }
           j.setAttributes(result);
           j.setSuccess(true);
       }
       return j;
    }

}
