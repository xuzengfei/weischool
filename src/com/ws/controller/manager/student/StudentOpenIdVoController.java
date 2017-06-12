package com.ws.controller.manager.student;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.student.StudentOpenIdVo;
import com.ws.service.student.StudentOpenIdVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 许增飞
 * @description 学生微信绑定
 * @datetime 2017/6/12 14:38.
 */
@Controller
@RequestMapping("/web/manager/studentopenidvo")
public class StudentOpenIdVoController {
    @Autowired
    private StudentOpenIdVoService service;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String toList(){
        return "/student/studentopenidvolist";
    }
    /**
     * 获得列表数据
     *
     * @param request  request对象
     * @param studentOpenIdVo 微信绑定对象值
     * @param datagrid 分页模型
     * @return
     */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, StudentOpenIdVo studentOpenIdVo, DataGrid<StudentOpenIdVo> datagrid) {
        service.datagrid(studentOpenIdVo, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 解除绑定
     * @param id 主键
     * @return
     */
    @RequestMapping(value ="/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id){
        service.del(id);
        return new AjaxJson("解绑成功",true,null);
    }
}
