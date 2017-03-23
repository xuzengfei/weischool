package com.ws.controller.wei.st.controller.exam;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.exam.Exam;
import com.ws.pojo.student.Student;
import com.ws.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 考试管理
 * Created by admin on 2017/3/8.
 */
@Controller
@RequestMapping("/wei/st/exam")
public class ExamApi {
    @Autowired
    private ExamService examService;


    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Exam exam, DataGrid<Exam> datagrid) {
        exam.setStudent(new Student(WeiStLoginUtils.getStudentSession().getStId()));
        examService.datagrid(exam, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

}
