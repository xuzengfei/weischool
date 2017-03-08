package com.ws.controller.manager.student;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campus.Campus;
import com.ws.pojo.grade.Grade;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.grade.GradeService;
import com.ws.service.student.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Controller
@RequestMapping("/web/manager/student/grade")
public class StudentGradeController {
    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/student/studentindex");
    }

    @RequestMapping(value = "/{cpId}", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable String cpId, String cpName) {
        return new ModelAndView("/student/studentgradelist").addObject("cpId", cpId).addObject("cpName", cpName);
    }
    
    @RequestMapping(value = "/{cpId}/{graId}", method = RequestMethod.GET)
    public ModelAndView list1(@PathVariable String cpId,@PathVariable String graId,String graName,String teacher) {
        return new ModelAndView("/student/studentgradelist1").addObject("cpId", cpId).addObject("graId", graId).addObject("graName", graName).addObject("teacher", teacher);
    }

    /**
     * 获得列表数据
     *
     * @param request
     * @param studentGrade
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "/datagrid/{cpId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, StudentGrade studentGrade, DataGrid<StudentGrade> datagrid, @PathVariable String cpId) {
        studentGrade.setCampus(new Campus(cpId));
        studentGradeService.datagrid(studentGrade, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }
    /**
     * 获得列表数据
     *
     * @param request
     * @param studentGrade
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "/datagrid/{cpId}/{graId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid1(HttpServletRequest request, StudentGrade studentGrade, DataGrid<StudentGrade> datagrid, @PathVariable String cpId, @PathVariable String graId) {
    	Grade grade = new Grade();
    	grade.setId(graId);
    	studentGrade.setCampus(new Campus(cpId));
        studentGrade.setGrade(grade);
        studentGradeService.datagrid(studentGrade, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 进入详情
     *
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/view/{cpId}/{id}", method = RequestMethod.GET)
    public ModelAndView toview(@PathVariable String id,@PathVariable String cpId) {
        return new ModelAndView("/student/studentgradeview").addObject("obj", studentGradeService.get(id)).addObject("cpId",cpId);
    }


    /**
     * 进入添加界面
     * @param cpId 校区ID
     * @param gradId 如果为-1的时候，则说明没有传入班级ID
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/add/{cpId}/{gradId}", method = RequestMethod.GET)
    public ModelAndView toadd(@PathVariable String cpId,@PathVariable String gradId,String graName) {
        Grade grade = new Grade();
        grade.setIsenable(1);
        grade.setCampus(new Campus(cpId));
        return new ModelAndView("/student/studentgradeadd").addObject("cpId", cpId).addObject("gradelist", gradeService.find(grade)).addObject("gradId",gradId).addObject("graName",graName);
    }

    /**
     * 进入修改界面
     *
     * @param id 主键
     * @return /student/studentadd
     */
    @RequestMapping(value = "/to/edit/{cpId}/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String cpId, @PathVariable String id) {
        Grade grade = new Grade();
        grade.setIsenable(1);
        grade.setCampus(new Campus(cpId));
        return new ModelAndView("/student/studentgradeadd").addObject("obj", this.studentGradeService.get(id)).addObject("gradelist", gradeService.find(grade));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(StudentGrade studentGrade) {
        ResultCode code = this.studentGradeService.add(studentGrade);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            case EXIST:
                j = new AjaxJson("已经存在", false, ResultCode.EXIST.getValue());
                break;
            case NOEXIST:
                j = new AjaxJson("账号不存在", false, ResultCode.NOEXIST.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(StudentGrade studentGrade) {
        ResultCode code = this.studentGradeService.edit(studentGrade, true);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            case EXIST:
                j = new AjaxJson("已经存在", false, ResultCode.EXIST.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    /**
     * 启用/禁用
     *
     * @param id     传入主键ID
     * @param status 启用或者禁用标记
     * @return 返回 AjaxJson，具体的值参考 AjaxJson
     */
    // TODO
    @RequestMapping(value = "/isenable/{id}/{status}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson isenable(@PathVariable String id, @PathVariable Integer status) {
        AjaxJson j = new AjaxJson();
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setId(id);
        studentGrade.setIsenable(status);
        studentGrade.setDelFlag(null);// 因为delFlag有个默认值
        ResultCode code = this.studentGradeService.edit(studentGrade, false);
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", false, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
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
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setId(id);
        studentGrade.setDelFlag(1);
        studentGrade.setDelTime(System.currentTimeMillis());
        ResultCode code = this.studentGradeService.edit(studentGrade, false);
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", false, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }
}
