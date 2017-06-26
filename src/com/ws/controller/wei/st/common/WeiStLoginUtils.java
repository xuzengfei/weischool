package com.ws.controller.wei.st.common;

import com.bugframework.common.utility.ResourceUtil;
import com.ws.pojo.student.StudentOpenId;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/2/14.
 */
public class WeiStLoginUtils {
    public static StudentOpenId getStudentSession() {
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            StudentOpenId s =(StudentOpenId)session.getAttribute("STUDENT");
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
        ResourceUtil.getSession().invalidate();
    }
}

