package com.ws.controller.manager.exame;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.exam.Exam;
import com.ws.service.exam.ExamService;
import com.ws.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2017/3/8.
 */
@Controller
@RequestMapping("/web/manager/exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(String stId) {
        return new ModelAndView("/exam/examlist").addObject("stId", stId);
    }

    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Exam exam,
                             DataGrid<Exam> datagrid) {
        examService.datagrid(exam, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public ModelAndView toadd(String stId) {
        return new ModelAndView("/exam/examadd", "stId", stId);
    }

    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id) {
        return new ModelAndView("/exam/examadd", "obj", this.examService.get(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Exam exam) {
        return new AjaxJson().result(this.examService.add(exam));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Exam exam) {
        return new AjaxJson().result(this.examService.edit(exam));
    }

    /**
     * 通过/不通过
     *
     * @param id     传入主键ID
     * @param status 通过/不通过标记
     * @return 返回 AjaxJson，具体的值参考 AjaxJson
     */
    // TODO
    @RequestMapping(value = "/status/{id}/{status}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson status(@PathVariable String id, @PathVariable Integer status) {

        Exam exam = new Exam();
        exam.setId(id);
        exam.setStatus(status);
        exam.setDelFlag(null);// 因为delFlag有个默认值
        return new AjaxJson().result(this.examService.edit(exam));
    }

    /**
     * 删除，逻辑删除
     *
     * @param id 传入ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        AjaxJson j = new AjaxJson();
        Exam exam = new Exam();
        exam.setId(id);
        exam.setDelFlag(1);
        exam.setDelTime(System.currentTimeMillis());
        return new AjaxJson().result(this.examService.edit(exam));
    }
}
