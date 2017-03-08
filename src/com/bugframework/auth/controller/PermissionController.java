package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.pojo.Role;
import com.bugframework.auth.pojo.RoleModule;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.PermissionService;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/auth/permission.do")
public class PermissionController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(params = "permission")
    public ModelAndView permission(String roleid, HttpServletRequest request) {
        request.setAttribute("roleid", roleid);
        return new ModelAndView("/auth/permission");
    }

    @RequestMapping(params = "getTree")
    @ResponseBody
    public List<BaseTree> getTree(HttpServletRequest request, String id) {
        //  List<AuthModule>  listModule =	moduleService.find("from AuthModule a where a.delFlag =? and a.isenable =?  order by a.orderby asc,a.createTime desc",0,1);
        List<Module> listModule = moduleService.find(0, 1);//获得未删除并且已经启用的模块
        List<RoleModule> permi = permissionService.findPermissionByRole(id);

        List<BaseTree> list = new ArrayList<BaseTree>();
        for (Module a : listModule) {
            boolean checked = false;
            outer:
            for (int i = 0; i < permi.size(); i++) {
                if (permi.get(i).getModule().getId().equals(a.getId())) {
                    checked = true;
                    permi.remove(i);
                    break outer;
                }
            }

            list.add(new BaseTree.TreeBuilder(a.getPid(), a.getId(), a.getName())
                            .open(a.getId().equals("1") ? true : false)
                            .checked(checked)
                            .floor(a.getFloor())
                            .build()
            );

        }

        return list;

    }

    @RequestMapping(params = "saveTree")
    @ResponseBody
    public AjaxJson saveTree(String roleid, String id, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if (DataUtils.isStrNotEmpty(roleid)) {
                List<Module> modules = moduleService.get(id.split(","));
                Role role = roleService.get(roleid);
                List<RoleModule> permisstions = new ArrayList<RoleModule>();
                for (Module a : modules) {
                    RoleModule permission = new RoleModule();
                    permission.setModule(a);
                    permission.setRole(role);
                    permisstions.add(permission);
                }
                permissionService.delete("role.id", roleid);
                permissionService.add(permisstions);
                j.setMsg(ResourceUtil.ADD_SUCCESS);
                j.setSuccess(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }
}
