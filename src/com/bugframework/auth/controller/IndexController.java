package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.common.utility.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class IndexController {
	@Autowired
	private ModuleService moduleService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {

		List<Module> menu1 = new ArrayList<Module>();
		List<Module> menu2 = new ArrayList<Module>();
		if (ResourceUtil.getUserSession().getSysRole().getIsAdmin() == 1) {
			List<Module> modules = this.moduleService.find(0, 1);
			if (!modules.isEmpty()) {
				for (Module p : modules) {
					if (p.getFloor() == 1) {
						menu1.add(p);
					}
					if (p.getFloor() == 2) {
						menu2.add(p);
					}

				}
			}
		} else {
           /* List<RoleModule> permission = this.permissionService.getleftMenu(sysuser.getSysRole().getId());
            if (!permission.isEmpty()) {
                for (RoleModule p : permission) {
                    if (p.getModule().getFloor() == 1) {
                        menu.add(p.getModule());
                    }
                    if (p.getModule().getFloor() == 2) {
                        menu1.add(p.getModule());
                    }
                }
            }*/

		}
		return new  ModelAndView("/main").addObject("menu1",menu1).addObject("menu2",menu2);
	}
}
