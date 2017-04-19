package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.User;
import com.bugframework.auth.service.UserService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView userlist(HttpServletRequest request) {
        return new ModelAndView("/auth/userlist");
    }

    @RequestMapping(params = "userdatalist")
    public ModelAndView userdatalist(HttpServletRequest request) {
        return new ModelAndView("/userdatalist");
    }

    @RequestMapping(params = "contactslist")
    public ModelAndView contactslist(HttpServletRequest request) {
        return new ModelAndView("/contactslist");
    }

    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, User user,
                             DataGrid<User> datagrid) {
        userService.datagrid(user, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/to/personal", method = RequestMethod.GET)
    public ModelAndView toPersonal() {
        return new ModelAndView("/auth/personal", "user", ResourceUtil.getUserSession().getUser());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(User user) {
        user.setId(ResourceUtil.getUserSession().getUser().getId());
        this.userService.update(user);
        ResourceUtil.getUserSession().setUser(user);
        return new AjaxJson("更新成功", true, null);
    }

}
