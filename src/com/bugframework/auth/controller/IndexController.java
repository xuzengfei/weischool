package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.pojo.RoleModule;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.RoleModuleService;
import com.bugframework.common.utility.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleModuleService roleModuleService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {

        List<Module> menu1 = new ArrayList<Module>();
        List<Module> menu2 = new ArrayList<Module>();
        List<Module> shortcuts = new ArrayList<>();
        Map<String, String> perms = new ConcurrentHashMap<>();
        Map<String, String> menus = new ConcurrentHashMap<>();
        List<Module> modules = this.moduleService.find(0, 1);
        if (ResourceUtil.getUserSession().getSysRole().getIsAdmin() == 1) {
            if (!modules.isEmpty()) {
                for (Module p : modules) {
                    if (p.getFloor() == 1) {
                        menu1.add(p);
                    }
                    if (p.getFloor() == 2) {
                        menu2.add(p);
                    }
                    if (p.getShortcut() == 1) {
                        shortcuts.add(p);
                    }
                    if (p.getUrl() != null)
                        perms.put(p.getUrl(), p.getId());
                }
                menus = perms;
            }
        } else {
            List<RoleModule> reoleModules = this.roleModuleService.findByRole(ResourceUtil.getUserSession().getSysRole().getId());
                if (!modules.isEmpty()) {
                    for (Module p : modules) {
                        if (p.getUrl() != null)
                        menus.put(p.getUrl(), p.getId());
                    }
                }
                if (!reoleModules.isEmpty()) {
                    for (RoleModule p : reoleModules) {
                        if (p.getModule().getFloor() == 1) {
                            menu1.add(p.getModule());
                        }
                        if (p.getModule().getFloor() == 2) {
                            menu2.add(p.getModule());
                        }
                        if (p.getModule().getShortcut() == 1) {
                            shortcuts.add(p.getModule());
                        }
                        if (p.getModule().getUrl() != null)
                            perms.put(p.getModule().getUrl(), p.getModule().getId());
                    }
                }
            }

            ResourceUtil.setAuthModuleSession(perms);//存放权限
            ResourceUtil.setAllModuleSession(menus);
            return new ModelAndView("/main").addObject("menu1", menu1).addObject("menu2", menu2).addObject("user", ResourceUtil.getUserSession()).addObject("shortcuts", shortcuts);
        }
  /*  @RequestMapping(value = "/noauth",method = RequestMethod.GET)
    public ModelAndView noAuth(){
        return new ModelAndView("/noauth");
    }*/
    }
