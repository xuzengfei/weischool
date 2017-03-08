package com.ws.controller.wei.controller.student;

import com.bugframework.common.pojo.AjaxJson;
import com.ws.controller.wei.common.WeiLoginUtils;
import com.ws.pojo.campus.Campus;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.campus.CampusService;
import com.ws.service.student.StudentGradeService;
import com.ws.service.student.StudentService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/17.
 */
@Controller
@RequestMapping("/wei/st")
public class StudentApi {
    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CampusService campusService;
    @RequestMapping(value = "/grade",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getStGrade(){
        StudentOpenId s = WeiLoginUtils.getStudentSession();
        List<StudentGrade> list =  this.studentGradeService.findOrderRestClass(s.getCpId(),s.getStId());
        List<Map<String,Object>> mlist = new ArrayList<>();
        if(list==null||list.isEmpty()){
            Student student = new Student();
            student.setId(s.getId());
            student.setIsenable(1);
            List<Student> students =studentService.find(student);
            Campus campus = campusService.get(s.getCpId());
            if(students!=null&&students.isEmpty()){
                WeiLoginUtils.removeStudentSession();
                return new AjaxJson("你已经被禁用了，请联系管理员",false,null);
            }
            Map<String,Object> map = new HashedMap();
            map.put("stName",students.get(0).getName());
            map.put("restClass",0);
            map.put("gradeName","");
            map.put("cpName",campus.getName());
            mlist.add(map);
        }
        for (StudentGrade studentGrade:list){
            Map<String,Object> map = new HashedMap();
            map.put("stName",studentGrade.getStudentName());
            map.put("restClass",studentGrade.getRestClass());
            map.put("gradeName",studentGrade.getGrade().getName());
            map.put("cpName",studentGrade.getCampus().getName());
            mlist.add(map);
        }
        return new AjaxJson(null,true,mlist);
    }
}
