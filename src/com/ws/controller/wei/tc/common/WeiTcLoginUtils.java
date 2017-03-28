package com.ws.controller.wei.tc.common;

import com.bugframework.common.utility.ResourceUtil;

import com.ws.pojo.teacher.Teacher;
import com.ws.pojo.teacher.TeacherOpenId;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/3/23.
 */
public class WeiTcLoginUtils {
    public static TeacherOpenId getTeacherSession() {
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            return (TeacherOpenId) session.getAttribute("TEACHER");
        }
        return null;
    }

    public static void setTeacherSession(TeacherOpenId s) {
        if (s == null)
            return;
        HttpSession session = ResourceUtil.getSession();
        if (session != null) {
            session.setAttribute("TEACHER", s);
            session.setMaxInactiveInterval(1000 * 60 * 60);
        }
    }

    public static void removeTeacherSession() {
        ResourceUtil.getSession().removeAttribute("TEACHER");
        ResourceUtil.getSession().invalidate();
    }
}
