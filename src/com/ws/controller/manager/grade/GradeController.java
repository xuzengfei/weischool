package com.ws.controller.manager.grade;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campus.Campus;
import com.ws.pojo.grade.Grade;
import com.ws.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web/manager/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "/grade/gradeindex";
    }

    @RequestMapping(value = "/{cpId}", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable String cpId,String cpName) {
        return new ModelAndView("/grade/gradelist").addObject("cpId", cpId).addObject("cpName",cpName);
    }

    /**
     * 获得列表数据
     *
     * @param request
     * @param grade
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "/datagrid/{cpId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Grade grade,
                             DataGrid<Grade> datagrid,@PathVariable String cpId) {
        grade.setCampus(new Campus(cpId));
        gradeService.datagrid(grade, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 进入添加界面
     *
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/add/{cpId}", method = RequestMethod.GET)
    public ModelAndView toadd(@PathVariable String cpId) {
        return new ModelAndView("/grade/gradeadd").addObject("cpId",cpId);
    }

    /**
     * 进入修改界面
     *
     * @param id 主键
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/edit/{cpId}/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id,@PathVariable String cpId) {
        return new ModelAndView("/grade/gradeadd").addObject("obj", this.gradeService.get(id)).addObject("cpId",cpId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Grade grade) {
        ResultCode code = this.gradeService.add(grade);
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Grade grade) {
        ResultCode code = this.gradeService.update(grade, true);
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
        Grade grade = new Grade();
        grade.setId(id);
        grade.setIsenable(status);
        grade.setDelFlag(null);// 因为delFlag有个默认值
        ResultCode code = this.gradeService.update(grade, false);
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
        Grade grade = new Grade();
        grade.setId(id);
        grade.setDelFlag(1);
        grade.setDelTime(System.currentTimeMillis());
        ResultCode code = this.gradeService.update(grade, false);
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
