package com.ws.controller.wei.tc.common;

import com.bugframework.common.utility.ResourceUtil;

import com.ws.pojo.teacher.Teacher;
import com.ws.pojo.teacher.TeacherOpenId;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/3/23.
 */
public class WeiTcLoginUtils {
    public static TeacherOpenId getStudentSession() {
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            return (TeacherOpenId) session.getAttribute("STUDENT");
        }
        return null;
    }

    public static void setStudentSession(TeacherOpenId s) {
        if (s == null)
            return;
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            session.setAttribute("STUDENT", s);
            session.setMaxInactiveInterval(1000 * 60 * 60);
        }
    }

    public static void removeStudentSession() {
        ResourceUtil.getSession().removeAttribute("STUDENT");
        ResourceUtil.getSession().invalidate();
    }
}
