package com.ws.controller.manager.teacher;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.teacher.TeacherOpenIdVo;
import com.ws.service.teacher.TeacherOpenIdVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 许增飞
 * @description 老师微信绑定
 * @datetime 2017/6/12 14:38.
 */
@Controller
@RequestMapping("/web/manager/teacheropenidvo")
public class TeacherOpenIdVoController {
    @Autowired
    private TeacherOpenIdVoService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toList() {
        return "/teacher/teacheropenidvolist";
    }

    /**
     * 获得列表数据
     *
     * @param request         request对象
     * @param teacherOpenIdVo 微信绑定对象值
     * @param datagrid        分页模型
     * @return
     */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, TeacherOpenIdVo teacherOpenIdVo, DataGrid<TeacherOpenIdVo> datagrid) {
        service.datagrid(teacherOpenIdVo, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 解除绑定
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        service.del(id);
        return new AjaxJson("解绑成功", true, null);
    }
}
