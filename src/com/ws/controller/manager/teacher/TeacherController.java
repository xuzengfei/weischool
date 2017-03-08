package com.ws.controller.manager.teacher;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.teacher.Teacher;
import com.ws.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 教师管理控制器
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/18
 */
@Controller
@RequestMapping("web/manager/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转到教师列表界面
     *
     * @return 返回teacher/teacherlist教师列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "teacher/teacherlist";
    }

    /**
     * 获得教师列表
     *
     * @param request  请求信息
     * @param teacher  教师信息
     * @param datagrid 分页参数集
     * @return AjaxJson封装的List教师信息集
     */
    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Teacher teacher, DataGrid<Teacher> datagrid) {
        teacherService.datagrid(teacher, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 跳转到添加教师界面
     *
     * @return ModelAndView封装的/teacher/teacheradd界面
     */
    @RequestMapping(value = "to/add")
    public ModelAndView toAdd() {
        return new ModelAndView("/teacher/teacheradd");
    }

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     * @return 返回AjaxJson封装操作状态信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Teacher teacher) {
        ResultCode code = this.teacherService.add(teacher);
        AjaxJson j;
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    /**
     * 跳转到修改教师信息界面
     *
     * @param id 教师id
     * @return 返回 /teacher/teacheradd界面
     */
    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable String id) {
        return new ModelAndView("/teacher/teacheradd").addObject("obj", this.teacherService.get(id));
    }

    /**
     * 修改教师信息
     *
     * @param teacher 教师信息
     * @return 返回AjaxJson封装的修改操作状态信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Teacher teacher) {
        ResultCode code = this.teacherService.update(teacher);
        AjaxJson j;
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    /**
     * 逻辑删除教师
     *
     * @param id 教师id
     * @return 返回AjaxJson封装的删除操作状态信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setDelFlag(1);
        teacher.setDelTime(System.currentTimeMillis());
        ResultCode code = this.teacherService.update(teacher);
        AjaxJson j;
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    /**
     * 启用/禁用教师
     *
     * @param id       教师id
     * @param isenable 启用、禁用标识
     * @return 返回AjaxJson封装的操作状态信息
     */
    @RequestMapping(value = "/isenable/{id}/{isenable}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson isenable(@PathVariable String id, @PathVariable Integer isenable) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setIsenable(isenable);
        ResultCode code = this.teacherService.update(teacher);
        AjaxJson j;
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }
}
