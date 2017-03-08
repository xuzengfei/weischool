package com.ws.controller.wei.controller.course;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.pojo.PageModel;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.controller.wei.common.WeiLoginUtils;
import com.ws.pojo.experience.Experience;
import com.ws.pojo.grade.ClassRegister;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.grade.ClassRegisterService;
import com.ws.service.student.StudentGradeService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/20.
 */
@Controller
@RequestMapping("/wei/course")
public class CourseApi {
    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private ClassRegisterService classRegisterService;


    @RequestMapping(value = "/to/choose", method = RequestMethod.GET)
    public String toChoose() {
        return "/wei/classQuery_choose";
    }

    @RequestMapping(value = "/to/choose/{id}", method = RequestMethod.GET)
    public ModelAndView toQuery(@PathVariable("id") String id) {

        return new ModelAndView("/wei/classQuery","id",id);
    }

    @RequestMapping(value = "/list/grade", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listGrade() {
        StudentOpenId studentOpenId = WeiLoginUtils.getStudentSession();
        List<StudentGrade> studentGrades = this.studentGradeService.findOrderRestClass(studentOpenId.getCpId(), studentOpenId.getStId());
        List<Map<String, String>> mlist = new ArrayList<>();
        for (StudentGrade st : studentGrades) {
            Map<String, String> map = new HashedMap();
            map.put("id", st.getId());
            map.put("name", st.getGrade().getName());
            mlist.add(map);
        }
        return new AjaxJson(null, true, mlist);
    }

    @RequestMapping(value = "/class/register/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listClassRegister(HttpServletRequest request, ClassRegister classRegister, DataGrid<ClassRegister> datagrid) {
        classRegister.setStId(WeiLoginUtils.getStudentSession().getStId());
        classRegister.setIsenable(1);
        List<String[]>  orders  = new ArrayList<>();
        String[] order  = new String[2];
        order[0] = "createTime";
        order[1]="desc";
        orders.add(order);
        datagrid.setOrder(orders);
        classRegisterService.datagrid(classRegister, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }
    @RequestMapping(value = "/rest/class/{gradeId}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson restClass(@PathVariable("gradeId")String gradeId){
        StudentGrade studentGrade = studentGradeService.get(gradeId);
        Map<String,String> result =new HashedMap();
        result.put("restClass",studentGrade.getRestClass()+"");
        result.put("gradeName",studentGrade.getGrade().getName());

        return  new AjaxJson("",true,result);
    }

}
