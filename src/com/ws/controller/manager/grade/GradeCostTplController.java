package com.ws.controller.manager.grade;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.grade.GradeCostTpl;
import com.ws.service.grade.GradeCostTplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 班吉课时费用
 * Created by admin on 2017/3/17.
 */
@Controller
@RequestMapping("/web/manager/grade/costtpl")
public class GradeCostTplController {
    @Autowired
    private GradeCostTplService service;

    /**
     * 进入列表
     *
     * @param gradeId 班级ID
     * @return 界面
     */
    @RequestMapping(value = "/{gradeId}", method = RequestMethod.GET)
    public ModelAndView toList(@PathVariable String gradeId, String graName) {
        return new ModelAndView("/grade/gradecosttpl", "gradeId", gradeId).addObject("gradeName", graName);
    }

    /**
     * 分页查询
     *
     * @param g        查询对象
     * @param dataGrid 分页模型
     * @param request  HttpServletRequest实例
     * @return ajax
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public AjaxJson datagrid(GradeCostTpl g, DataGrid<GradeCostTpl> dataGrid, HttpServletRequest request) {
        this.service.datagrid(g, dataGrid, request);
        return HqlGenerateUtil.datagrid(dataGrid);
    }

    /**
     * 进入添加界面
     *
     * @param gradeId 班级ID
     * @return
     */
    @RequestMapping(value = "/to/add/{gradeId}", method = RequestMethod.GET)
    public ModelAndView toAdd(@PathVariable String gradeId) {
        return new ModelAndView("/grade/gradecosttpladd", "gradeId", gradeId);
    }

    /**
     * 进入更新界面
     *
     * @param gradeId 班级ID
     * @return
     */
    @RequestMapping(value = "/to/edit/{gradeId}/{id}", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable String gradeId, @PathVariable String id) {
        return new ModelAndView("/grade/gradecosttpladd", "gradeId", gradeId).addObject("obj", this.service.get(id));
    }

    /**
     * 保存
     *
     * @param g 对象值
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(GradeCostTpl g) {
        return new AjaxJson().result(this.service.add(g));
    }

    /**
     * 更新
     *
     * @param g 对象值
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(GradeCostTpl g) {
        return new AjaxJson().result(this.service.edit(g));
    }

    /**
     * 删除
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        return new AjaxJson().result(this.service.del(id));
    }
}
