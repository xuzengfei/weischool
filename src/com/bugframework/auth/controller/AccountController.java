package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.Role;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.auth.service.AccountService;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * Title:用户帐号管理
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 许增飞
 * @date 2016-10-20 下午11:48:27
 */
@Controller
@RequestMapping("/auth/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView userlist(HttpServletRequest request) {
        List<Role> roleList = roleService.getRoleList();
        String roleStr = "";
        for (Role role : roleList) {
            roleStr += "," + role.getId() + "_" + role.getName();
        }
        if (roleStr.length() > 0) {
            roleStr = roleStr.substring(1);
        }
        request.setAttribute("roleStr", roleStr);
        return new ModelAndView("/auth/accountlist");
    }

    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, UserAccount account,
                             DataGrid<UserAccount> datagrid) {
        accountService.datagrid(account, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 进入添加界面
     *
     * @return
     */
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public ModelAndView useradd() {
        return new ModelAndView("/auth/accountadd");
    }

    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView useredit(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("account", this.accountService.get(id));
        return new ModelAndView("/auth/accountadd");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(UserAccount account) {
        ResultCode code = this.accountService.add(account);
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
    public AjaxJson edit(UserAccount account) {
        if (account.getSysRole().getId() == null || "".equals(account.getSysRole().getId()))
            account.setSysRole(null);
        ResultCode code = this.accountService.edit(account);
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
    @RequestMapping(value = "/isenable/{id}/{status}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson isenable(@PathVariable String id,
                             @PathVariable Integer status) {
        UserAccount account = new UserAccount();
        account.setId(id);
        account.setIsenable(status);
        account.setDelFlag(null);// 因为delFlag有个默认值
        return new AjaxJson().result(this.accountService.update(account));
    }

    @RequestMapping(value = "/reset/{id}/{pswd}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson resetPswd(@PathVariable String id, @PathVariable String pswd) {
        UserAccount account = new UserAccount();
        account.setId(id);
        account.setPassword(pswd);
        account.setDelFlag(null);// 因为delFlag有个默认值
        return new AjaxJson().result(this.accountService.update(account));
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
        UserAccount account = new UserAccount();
        account.setId(id);
        account.setDelFlag(1);
        account.setDelTime(System.currentTimeMillis());
        ResultCode code = this.accountService.update(account);
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }


    @RequestMapping(value = "/to/repassword", method = RequestMethod.GET)
    public ModelAndView toRepassword() {
        return new ModelAndView("/auth/repswd", "userAccount", ResourceUtil.getUserSession());
    }

    @RequestMapping(value = "/{id}/newpswd/{newP}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson changePswd(@PathVariable String id, @PathVariable String newP) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        userAccount.setPassword(newP);
        this.accountService.edit(userAccount);
        return null;
    }

}
