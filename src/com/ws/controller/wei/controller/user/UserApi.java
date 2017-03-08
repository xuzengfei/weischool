package com.ws.controller.wei.controller.user;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.ResultCode;
import com.ws.controller.wei.common.WeiLoginUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/15.
 */
@Controller
@RequestMapping("/wei/user")
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
        service.toCodeURl(request, response);
    }

    @RequestMapping(value = "/authorization")
    public String authorization(HttpServletRequest request) {
     //   String openId = service.getOpenId(request);
        String openId = "123456";
        if (openId == null) {
            request.setAttribute("errorMsg", "微信请求失败！");
            return "/wei/error";
        }
        StudentOpenId s = service.getByOpenid(openId);
        if (s == null) {
            request.setAttribute("openId", openId);
            return "/wei/login";
        }

        WeiLoginUtils.setStudentSession(s);
        return "/wei/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson login(String openId, String stNo, String cpId) {
        ResultCode code = studentOpenIdService.save(openId, stNo, cpId);
        if (code.getValue() == 1) {
            WeiLoginUtils.setStudentSession(service.getByOpenid(openId));
        }
        return new AjaxJson().result(code);
    }

    @RequestMapping(value = "/get/msg", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getMsg() {
        Student student = studentService.get(WeiLoginUtils.getStudentSession().getStId());
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudent(student);
        studentGrade.setIsenable(1);
        List<StudentGrade> list = studentGradeService.find(studentGrade);
        Map<String, Object> map = new HashedMap();
        return null;
    }
}
