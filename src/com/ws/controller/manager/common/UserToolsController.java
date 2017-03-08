package com.ws.controller.manager.common;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.campus.Campus;
import com.ws.pojo.grade.Grade;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.teacher.Teacher;
import com.ws.service.campus.CampusService;
import com.ws.service.grade.GradeService;
import com.ws.service.student.StudentGradeService;
import com.ws.service.student.StudentService;
import com.ws.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选人控件
 * Created by admin on 2017/2/7.
 */
@Controller
@RequestMapping("/web/manager/utools")
public class UserToolsController {
    @Autowired
    private CampusService campusService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentGradeService  studentGradeService;

    @RequestMapping("/to/{module}/{type}")
    public String toStRadio(@PathVariable String module, @PathVariable Integer type) {
        if ("st".equals(module) && type == 0)
            return "/usertools/studentselect";
        else if ("tc".equals(module) && type == 0) {
            return "/usertools/teacherselect";
        }
        else if("st".equals(module)&&type==1){
            return "/usertools/studentselect1";
        }
        else{
            return "/usertools/studentselect2";
        }

    }

    /**
     * 获得启用的列表数据
     *
     * @param request
     * @param student
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "/st/datagrid/isenable/1", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid1(HttpServletRequest request, Student student, DataGrid<Student> datagrid) {
        student.setDelFlag(0);
        student.setIsenable(1);
        studentService.datagrid(student, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }


    /**
     * 获得教师列表
     *
     * @param request  请求信息
     * @param teacher  教师信息
     * @param datagrid 分页参数集
     * @return AjaxJson封装的List教师信息集
     */
    @RequestMapping(value = "/tc/datagrid/isenable/1", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Teacher teacher, DataGrid<Teacher> datagrid) {
        teacher.setDelFlag(0);
        teacher.setIsenable(1);
        teacherService.datagrid(teacher, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseTree> tree() {
        Campus campus = new Campus();
        campus.setIsenable(1);
        List<Campus> campusList = campusService.find(campus);
        List<BaseTree> list = new ArrayList<BaseTree>();
        for (Campus c : campusList) {
            list.add(new BaseTree.TreeBuilder("0", c.getId(), c.getName()).floor((short) 1).build()
            );
        }
        list.add(new BaseTree.TreeBuilder("-1", "0", "校区").open(true).floor((short) 0).build());
        if (campusList != null && campusList.size() > 0) {
            Grade grade = new Grade();
            grade.setIsenable(1);
            List<Grade> grades = this.gradeService.find(grade);
            for (Grade g : grades) {
                list.add(new BaseTree.TreeBuilder(g.getCpId(), g.getId(), g.getName()).floor((short) 2).build());
            }
        }
        return list;
    }
    @RequestMapping(value = "/userlist/{module}/{floor}/{id}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson userlist(@PathVariable String module,@PathVariable Integer floor,@PathVariable String id,String keyword){
        AjaxJson j = new AjaxJson();
        List<Map<String, String>> list = new ArrayList<>();
        if("st".equals(module)){
              if(floor==0){
                  Student student = new Student();
                  student.setIsenable(1);
                  student.setName(keyword);
                  List<Student> students = this.studentService.find(student);
                 
                  for (Student st :students){
                	  Map<String,String > stMap =new HashMap<>();
                	  stMap.put("id", st.getId());
                	  stMap.put("name", st.getName());
                	  list.add(stMap);
                  }
              }
              else if(floor==1||floor==2){
            	  StudentGrade sg = new StudentGrade();
            	  sg.setIsenable(1);
            	  if(floor==1){
            		  Campus cp  =   new Campus();
            		  cp.setId(id);
            		  sg.setCampus(cp);
            	  }
            	  else{
            		  Grade grade = new Grade();
            		  grade.setId(id);
            		  sg.setGrade(grade);
            	  }
            		 
                 List<StudentGrade> students =  this.studentGradeService.find(sg);
                 for (StudentGrade st :students){
                	  Map<String,String > stMap =new HashMap<>();
                      stMap.put("id", st.getStudent().getId());
                  	  stMap.put("name", st.getStudentName());
                  	  list.add(stMap);
                 }
              }

        }
        j.setObj(list);
        j.setSuccess(true);
        return  j;
    }
}
