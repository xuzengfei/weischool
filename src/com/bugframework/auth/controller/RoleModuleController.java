package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.RoleModule;
import com.bugframework.auth.service.RoleModuleService;
import com.bugframework.common.pojo.AjaxJson;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色权限
 * Created by admin on 2017/4/18.
 */

@Controller
@RequestMapping("/auth/rmodule")
public class RoleModuleController {
    @Autowired
    private RoleModuleService service;

    @RequestMapping(value = "/role/{role}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson get(@PathVariable String role) {
        List<RoleModule> list = this.service.findByRole(role);
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = null;
        for (RoleModule r : list) {
            map = new HashedMap();
            map.put("role", r.getRole());
            map.put("module", r.getModule().getId());
            result.add(map);
        }
        return new AjaxJson(null, true, result);
    }

}
