package com.ws.controller.wei.tc;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.ResultCode;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.controller.wei.tc.common.WeiTcLoginUtils;
import com.ws.pojo.teacher.Teacher;
import com.ws.pojo.teacher.TeacherOpenId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/3/23.
 */
@Controller
@RequestMapping("/wei/tc/login")
public class LoginApi {
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson login(String openId, String tcNo) {
        openId =tcNo;//TODO 暂时这样写
        ResultCode code = studentOpenIdService.save(openId, stNo, cpId);
        if (code.getValue() == 1) {
            WeiTcLoginUtils.setStudentSession(service.getByOpenid(openId));
        }
        return new AjaxJson().result(code);
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void code(HttpServletRequest request, HttpServletResponse response) {
   //     service.toCodeURl(request, response);
    }

    @RequestMapping(value = "/authorization")
    public String authorization(HttpServletRequest request) {
        //   String openId = service.getOpenId(request);
        String openId = "-1";//todo 测试用
        if (openId == null) {
            request.setAttribute("errorMsg", "微信请求失败！");
            return "/wei/error";
        }
      //  StudentOpenId s = service.getByOpenid(openId);
        TeacherOpenId s =null;
        if (s == null) {
            request.setAttribute("openId", openId);
            return "/wei/login";
        }
        WeiTcLoginUtils.setStudentSession(s);
        return "/wei/tc/index";
    }

}
