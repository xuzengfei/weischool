package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.pojo.Role;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
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
@RequestMapping("/auth/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;


    /**
     * 進入角色列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView rolelist(HttpServletRequest request) {
        return new ModelAndView("/auth/rolelist");
    }

    /**
     * 获得列表数据
     *
     * @param request
     * @param role
     * @param datagrid
     * @return
     */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Role role,
                             DataGrid<Role> datagrid) {
        roleService.datagrid(role, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 获得所有已经启用的，非删除的，非超级管理员的角色列表
     *
     * @return
     */
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAll() {
        return new AjaxJson("", true, roleService.getRoleList());
    }

    /**
     * 进入添加界面
     *
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public ModelAndView useradd() {
        return new ModelAndView("/auth/roleadd");
    }

    /**
     * 进入修改界面
     *
     * @param request http请求
     * @param id      主键
     * @return /auth/roleadd界面
     */
    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView useredit(HttpServletRequest request,
                                 @PathVariable String id) {
        request.setAttribute("role", this.roleService.get(id));
        return new ModelAndView("/auth/roleadd");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Role role, String[] modules) {
        ResultCode code = this.roleService.add(role, modules);
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
    public AjaxJson edit(Role role, String[] modules) {
        ResultCode code = this.roleService.update(role, true, modules);
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
        Role role = new Role();
        role.setId(id);
        role.setIsenable(status);
        role.setDelFlag(null);// 因为delFlag有个默认值
        ResultCode code = this.roleService.update(role, false);
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
     * 删除，物理删除<br>
     * <p>
     * 1.校验是否已经被分配用户，如果被分配用户，则不给删除
     * </p>
     * <p>
     * 2.删除成功要把权限给删除
     * </p>
     *
     * @param id 传入ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        AjaxJson j = new AjaxJson();
        ResultCode code = this.roleService.delete(id);
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法,或者该角色已经被用户使用", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", false, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    @RequestMapping(params = "getTree")
    @ResponseBody
    public List<BaseTree> getTree(HttpServletRequest request, String id) {
        List<Module> listModule = moduleService.find(0, 1);// 获得未删除并且已经启用的模块
        List<BaseTree> list = new ArrayList<BaseTree>();
      /*  for (Module a : listModule) {
            if (a.getId().equals("1")) {
                list.add(new BaseTree(a.getId(), a.getPid(), a.getName(), true,
                        false));
            } else {
                list.add(new BaseTree(a.getId(), a.getPid(), a.getName(),
                        false, false));
            }
        }*/

        return list;

    }
}
