package com.ws.controller.wei.common;

import com.bugframework.common.utility.ResourceUtil;
import com.ws.pojo.student.StudentOpenId;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by admin on 2017/2/14.
 */
public class WeiLoginUtils {
    public static StudentOpenId getStudentSession() {
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            return (StudentOpenId)session.getAttribute("STUDENT");
        }
        return null;
    }
    public static void setStudentSession(StudentOpenId s){
        if(s==null)
            return;
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            session.setAttribute("STUDENT",s);
            session.setMaxInactiveInterval(1000*60*60);
        }
    }
    public static void  removeStudentSession(){
        ResourceUtil.getSession().removeAttribute("STUDENT");
    }
}

