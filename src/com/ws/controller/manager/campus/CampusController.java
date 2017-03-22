package com.ws.controller.manager.campus;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campus.Campus;
import com.ws.service.campus.CampusService;
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

@Controller
@RequestMapping("/web/manager/campus")
public class CampusController {
    @Autowired
    private CampusService campusService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "/campus/campuslist";
    }

    /**
     * 获得树形的结构
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseTree> tree() {
        Campus campus = new Campus();
        campus.setIsenable(1);
        List<Campus> campusList = campusService.find(campus);
        List<BaseTree> list = new ArrayList<BaseTree>();
        for (Campus c : campusList) {
            list.add(new BaseTree.TreeBuilder("0", c.getId(), c.getName()).build()
            );
        }
        list.add(new BaseTree.TreeBuilder("-1", "0", "校区").open(true).build());
        return list;
    }

    /**
     * 获得列表数据
     *
     * @param request
     * @param campus
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Campus campus,
                             DataGrid<Campus> datagrid) {
        campusService.datagrid(campus, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 进入添加界面
     *
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public ModelAndView toadd() {
        return new ModelAndView("/campus/campusadd");
    }

    /**
     * 进入修改界面
     *
     * @param id 主键
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id) {
        return new ModelAndView("/campus/campusadd").addObject("obj", this.campusService.get(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Campus campus) {

        return new AjaxJson().result(this.campusService.add(campus));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Campus campus) {
        return new AjaxJson().result(this.campusService.update(campus, true));
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
        Campus campus = new Campus();
        campus.setId(id);
        campus.setIsenable(status);
        campus.setDelFlag(null);// 因为delFlag有个默认值
        return new AjaxJson().result(this.campusService.update(campus, false));
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
        Campus campus = new Campus();
        campus.setId(id);
        campus.setDelFlag(1);
        campus.setDelTime(System.currentTimeMillis());
        return new AjaxJson().result(this.campusService.update(campus, false));
    }
}
