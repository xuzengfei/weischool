package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth/module")
public class ModuleConroller {
    @Autowired
    private ModuleService moduleService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main() {
        return "/auth/modulemain";
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseTree> getTree() {
        List<Module> listModule = this.moduleService.find();
        List<BaseTree> list = new ArrayList<BaseTree>();
        for (Module a : listModule) {
            list.add(new BaseTree.TreeBuilder(a.getPid(), a.getId(), a.getName())
                    .open((a.getId().equals("1") || a.getId().equals("0")) ? true : false)
                    .checked(false)
                    .canDel(a.getIsAdmin() == 1 ? false : true)
                    .floor(a.getFloor())
                    .build()
            );

        }
        return list;

    }

    /**
     * 进入添加界面
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "/to/add/{pid}", method = RequestMethod.GET)
    public ModelAndView toadd(@PathVariable String pid, String pname, int floor) {
        return new ModelAndView("/auth/moduleadd", "pid", pid).addObject("pname", pname).addObject("floor", ++floor);
    }

    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id, String pname) {
        Module module = this.moduleService.get(id);
        return new ModelAndView("/auth/moduleadd").addObject("module", module).addObject("pid", module.getPid()).addObject("pname", pname);
    }

    @RequestMapping(params = "datagrid")
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Module module, DataGrid<Module> datagrid) {
        this.moduleService.datagrid(module, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 進入添加或者修改頁面
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "module")
    public ModelAndView module(String id, String pid, HttpServletRequest request) {
        if (!DataUtils.isStrEmpty(id)) {
            Module obj = this.moduleService.get(id);
            request.setAttribute("obj", obj);
        }
        request.setAttribute("pid", pid);
        return new ModelAndView("/auth/module");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Module module) {
        ResultCode code = this.moduleService.add(module);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            case EXIST:
                j = new AjaxJson("已经存在帐号", false, ResultCode.EXIST.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Module module) {
        ResultCode code = this.moduleService.update(module);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            case EXIST:
                j = new AjaxJson("已经存在帐号", false, ResultCode.EXIST.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    @RequestMapping(params = "del")
    @ResponseBody
    public AjaxJson del(Module module, HttpServletRequest request, HttpSession session) {
        AjaxJson j = new AjaxJson();
        try {
            if (!DataUtils.isStrEmpty(module.getId())) {
                if (module.getId().indexOf(",") > -1) {//批量删除
                    this.moduleService.deleteAlllogic(module.getId());
                } else {
                    this.moduleService.deletelogic(module.getId());
                }
                j.setMsg(ResourceUtil.DEL_SUCCESS);
                j.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping(params = "isenabled")
    @ResponseBody
    public AjaxJson isenabled(Module module, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if (!DataUtils.isStrEmpty(module.getId())) {
                Module s = this.moduleService.get(module.getId());
                s.setIsenable(module.getIsenable());
                this.moduleService.update(s);
                if (module.getIsenable() == 1)
                    j.setMsg(ResourceUtil.ENABLE_SUCCESS);
                else
                    j.setMsg(ResourceUtil.DISABLE_SUCCESS);
                j.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping(params = "isExist")
    @ResponseBody
    public ValidForm isExist(HttpServletRequest request, String id) {
        ValidForm v = new ValidForm();
        try {
            String param = request.getParameter("param");//获得该字段的值,新输入的值
            String pid = request.getParameter("pid");
            String olderValue = null;
            if (DataUtils.isStrNotEmpty(id)) {
                Module m = this.moduleService.get(id);
                if (m != null) olderValue = m.getName();
            }
            if (param.equals(olderValue)) {//这是更新操作的判断,如果相等，说明角色名称没有修改过，也不需要校验

            } else {
                List<Module> modules = this.moduleService.find(param, pid);
                if (modules.size() > 0) {
                    v.setStatus("n");
                    v.setInfo("【" + param + "】已经存在!");
                }
            }
        } catch (Exception e) {
            v.setStatus("n");
            v.setInfo("数据库异常");
            e.printStackTrace();
        }
        return v;
    }

    @RequestMapping(params = "moduleTreeSelect")
    public ModelAndView moduleTreeSelect(HttpServletRequest request, String pid) {
        return new ModelAndView("/auth/moduleTreeSelect");
    }

    /**
     * 获得模块信息
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "getmodule")
    @ResponseBody
    public AjaxJson getmodule(Module module, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        if (!DataUtils.isStrEmpty(module.getId())) {
            Module obj = this.moduleService.get(module.getId());
            j.setObj(obj);
            j.setSuccess(true);
        }
        return j;
    }

    @RequestMapping(value = "/ignore/sys/module", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson ignoreSysModule() {
        return new AjaxJson(null, true, this.moduleService.findIgnoreSys());
    }

}
