package com.ws.controller.wei.st.controller.user;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.student.StudentGradeService;
import com.ws.service.student.StudentOpenIdService;
import com.ws.service.student.StudentService;
import com.ws.service.weixin.WeixinLoginService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/15.
 */
@Controller
@RequestMapping("/wei/st/user")
public class UserApi {
    @Autowired
    private WeixinLoginService service;
    @Autowired
    private StudentOpenIdService studentOpenIdService;
    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void code(HttpServletRequest request, HttpServletResponse response) {
        service.toCodeURl(request, response, "st");
    }
    //todo修改学生登陆
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorization(HttpServletRequest request) {
        String openId = service.getOpenId(request);
        // String openId = "-1";//todo 测试用
        if (openId == null) {
            request.setAttribute("errorMsg", "微信请求失败！");
            return "/wei/error";
        }
        List<StudentOpenId> studentOpenIds = service.getListByOpenId(openId);
        if (studentOpenIds == null) {
            request.setAttribute("openId", openId);
            return "/wei/login";
        }
        WeiStLoginUtils.setStudentSession(studentOpenIds.get(0));
        if(studentOpenIds.size()==1){
            return "redirect:" + ResourceUtil.basePath(request) + "wei/st/to/index";
        }else{
            for (StudentOpenId s:studentOpenIds) {
                s.setStName(this.studentService.get(s.getStId()).getName());
            }
            request.setAttribute("sps",studentOpenIds);
            return "/wei/chooseLogin";
        }
    }
    @RequestMapping(value = "/to_user", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson toUser(StudentOpenId studentOpenId,HttpServletRequest request){
        if (studentOpenId.getCpId()==null||studentOpenId.getStId()==null||studentOpenId.getOpenId()==null||studentOpenId.getId()==null)
            return new AjaxJson(null,false,null);
        if (WeiStLoginUtils.getStudentSession().getStId().equals(studentOpenId.getStId())){
            WeiStLoginUtils.setStudentSession(studentOpenId);
        }
        return new AjaxJson(null,true,null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson login(String openId, String stNo, String cpId) {
        //   openId =stNo;//TODO 暂时这样写
        ResultCode code = studentOpenIdService.save(openId, stNo, cpId);
        if (code.getValue() == 1) {
            WeiStLoginUtils.setStudentSession(service.getByOpenId(openId));
        }
        return new AjaxJson().result(code);
    }

    @RequestMapping(value = "/get/msg", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getMsg() {
        Student student = studentService.get(WeiStLoginUtils.getStudentSession().getStId());
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudent(student);
        studentGrade.setIsenable(1);
        List<StudentGrade> list = studentGradeService.find(studentGrade);
        Map<String, Object> map = new HashedMap();
        return null;
    }

    /**
     * 更新校区
     *
     * @param cp 校区ID
     * @return AjaxJson
     */
    @RequestMapping(value = "/change/cp/{cp}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson chanageCp(@PathVariable String cp) {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setIsenable(1);
        List<StudentGrade> list = this.studentGradeService.find(studentGrade);
        for (StudentGrade sg : list) {
            if (sg.getCpId().equals(cp)) {
                StudentOpenId studentOpenId = WeiStLoginUtils.getStudentSession();
                //    WeiStLoginUtils.removeTeacherSession();
                studentOpenId.setCpId(cp);
                WeiStLoginUtils.setStudentSession(studentOpenId);
                return new AjaxJson(null, true, null);
            }
        }
        return new AjaxJson(null, false, null);
    }

}
