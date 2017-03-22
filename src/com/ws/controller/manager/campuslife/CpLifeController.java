package com.ws.controller.manager.campuslife;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.campuslife.CpLife;
import com.ws.service.campuslife.CpLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/9.
 */
@Controller
@RequestMapping("/web/manager/cplife")
public class CpLifeController {
    @Autowired
    private CpLifeService cpLifeService;

    @RequestMapping(value = "/to/list/{cpId}", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable  String cpId) {
        return new ModelAndView("campuslife/cplifelist", "cpId", cpId);
    }

    /**
     * 获得列表数据
     *
     * @param request
     * @param cpLife
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, CpLife cpLife, DataGrid<CpLife> datagrid) {
        cpLifeService.datagrid(cpLife, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 进入添加界面
     *
     * @return /campuslife/cpLifeAdd界面
     */
    @RequestMapping(value = "/to/add/{cpId}", method = RequestMethod.GET)
    public ModelAndView toadd(@PathVariable String cpId) {
        return new ModelAndView("/campuslife/cplifeadd","cpId",cpId);
    }

    /**
     * 添加校园生活
     *
     * @param cpLife 对象信息
     * @return 返回AjaxJson
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(CpLife cpLife) {
        return new AjaxJson().result(this.cpLifeService.add(cpLife));
    }

    /**
     * 进入修改界面
     *
     * @param id 主键
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id) {
        return new ModelAndView("/campuslife/cplifeadd", "obj", this.cpLifeService.get(id));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(CpLife cpLfe) {
        return new AjaxJson().result(this.cpLifeService.edit(cpLfe));
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
        CpLife cpLife = new CpLife();
        cpLife.setId(id);
        cpLife.setIsenable(status);
        cpLife.setDelFlag(null);// 因为delFlag有个默认值
        return this.edit(cpLife);
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
        CpLife cpLife = new CpLife();
        cpLife.setId(id);
        cpLife.setDelFlag(1);
        cpLife.setDelTime(System.currentTimeMillis());
        return this.edit(cpLife);
    }
}
