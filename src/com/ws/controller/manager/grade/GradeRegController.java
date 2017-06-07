package com.ws.controller.manager.grade;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.grade.GradeReg;
import com.ws.service.grade.GradeRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 许增飞
 * @description 上课签到
 * @datetime 2017/6/7 11:50.
 */
@Controller
@RequestMapping("/web/manager/gradereg")
public class GradeRegController {
    @Autowired
    private GradeRegService gradeRegService;

    /**
     * 跳转到班次学生签到信息界面
     *
     * @param gtId 班次ID
     * @return ModelAndView
     */
    @RequestMapping("/to/gtId/{gtId}")
    public ModelAndView toList(@PathVariable String gtId) {
        return new ModelAndView("/grade/coursereg", "gtId", gtId);
    }

    /**
     * 获得该班次下的学生签到信息
     *
     * @param request  request对象
     * @param gtId     班次ID
     * @param gradeReg 签到信息
     * @param datagrid 分页模型
     * @return AjaxJson
     */
    @RequestMapping(value = "/gtId/{gtId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listGradeRegister(HttpServletRequest request, @PathVariable String gtId, GradeReg gradeReg, DataGrid<GradeReg> datagrid) {
        gradeReg.setGtId(gtId);
        List<String[]> orders = new ArrayList<>();
        String[] order = new String[2];
        order[0] = "ct";
        order[1] = "desc";
        orders.add(order);
        datagrid.setOrder(orders);
        gradeRegService.datagrid(gradeReg, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * TODO 更新点名
     *
     * @param id     主键
     * @param status 状态：1-准 2-请 3-旷 4-迟
     * @return
     */
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson editSign(@PathVariable String id, @PathVariable Short status) {
        if (id == null)
            return new AjaxJson("更新失败", false, null);
        gradeRegService.edit(id, status);
        return new AjaxJson("更新成功", true, null);
    }
}
