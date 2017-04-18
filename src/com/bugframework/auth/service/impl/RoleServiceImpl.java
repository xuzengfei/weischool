package com.bugframework.auth.service.impl;

import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.pojo.Role;
import com.bugframework.auth.pojo.RoleModule;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.auth.service.RoleModuleService;
import com.bugframework.auth.service.RoleService;
import com.bugframework.auth.service.UserService;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:RoleServiceImpl </p>
 * <p>Description:角色服务实现层 </p>
 *
 * @author 许增飞
 * @date 2016-8-14 上午9:17:20
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleModuleService roleModuleService;
    @Autowired
    private UserService userService;

    @Override
    public List<Role> getRoleList() {
        return this.roleDao.getRoleList(new Role((short) 0, 1, 0));

    }

    @Override
    public Role get(String id) {
        if (id == null)
            return new Role();
        return this.roleDao.get(id);
    }

    @Override
    public void datagrid(Role role, DataGrid<Role> datagrid,
                         HttpServletRequest request) {
        this.roleDao.datagrid(role, datagrid, request);

    }

    @Override
    public ResultCode update(Role role, boolean isNameExistValid, String[] modules) {

        if (role.getId() == null)
            return ResultCode.INVALID;
        if (isNameExistValid && role.getName() != null) {
            Role r = this.get(role.getId());
            if (!role.getName().equals(r.getName()) && !this.findByName(role.getName()).isEmpty())
                return ResultCode.EXIST;
        }
        this.roleDao.update(role);
        this.roleModuleAddOrUp(modules, role.getId(), true);
        return ResultCode.SUCCESS;


    }

    @Override
    public ResultCode update(Role role, boolean isNameExistValid) {

        if (role.getId() == null)
            return ResultCode.INVALID;
        if (isNameExistValid && role.getName() != null) {
            Role r = this.get(role.getId());
            if (!role.getName().equals(r.getName()) && !this.findByName(role.getName()).isEmpty())
                return ResultCode.EXIST;
        }
        this.roleDao.update(role);
        return ResultCode.SUCCESS;


    }

    @Override
    public void deleteAlllogic(String id) {
        this.roleDao.deleteAlllogic(id);

    }

    @Override
    public ResultCode delete(String id) {
        if (id == null)
            return ResultCode.INVALID;
        List<UserAccount> list = userService.findUser("sysRole.id", id);
        if (list != null && list.size() > 0)
            return ResultCode.INVALID;
        this.roleModuleService.delete(id,(short) 1);
        this.roleDao.delete(id);
        return ResultCode.SUCCESS;
    }

    /**
     * @param role    角色对象
     * @param modules
     * @return
     */
    @Override
    public ResultCode add(Role role, String[] modules) {
        if (role.getName() == null)
            return ResultCode.INVALID;
        if (!this.findByName(role.getName()).isEmpty())
            return ResultCode.EXIST;
        this.roleDao.add(role);
        roleModuleAddOrUp(modules, role.getId(), false);
        return ResultCode.SUCCESS;

    }

    /**
     * 权限添加或者修改
     *
     * @param modules 模块ID数组
     * @param roleId  角色ID
     * @param isEdit  更新或者添加
     */
    public void roleModuleAddOrUp(String[] modules, String roleId, boolean isEdit) {
        if (isEdit)
            this.roleModuleService.delete(roleId, (short) 1);
        if (modules != null) {
            List<RoleModule> roleModules = new ArrayList<>();
            for (int i = 0; i < modules.length; i++) {
                roleModules.add(new RoleModule(roleId, new Module(modules[i])));
            }
            this.roleModuleService.add(roleModules, roleId);
        }
    }

    @Override
    public List<Role> findByName(String name) {
        return this.roleDao.find(" from Role r where r.name=?", name);
    }


}
