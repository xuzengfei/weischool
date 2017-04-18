package com.bugframework.auth.service.impl;

import com.bugframework.auth.dao.ModuleDao;
import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.RoleModuleService;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:服务接口实现层</p>
 * <p>Description: 服务接口实现层</p>
 *
 * @author 许增飞
 * @date 2016-8-14 下午12:31:09
 */

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private RoleModuleService roleModuleService;

    @Override
    public List<Module> find(int delFlag, int iseabled) {
        return moduleDao
                .find("from Module a where a.delFlag =? and a.isenable =? order by orderby asc,createTime desc",
                        delFlag, iseabled);
    }

    @Override
    public List<Module> find() {
        return this.moduleDao
                .find("from Module a where a.delFlag =? order by a.orderby asc,a.createTime desc",
                        0);
    }

    @Override
    public List<Module> find(String name, String pid) {
        return this.moduleDao.find(
                " from Module r where r.name=? and  r.pid =?", name, pid);
    }

    @Override
    public List<Module> findIgnoreSys() {
        return this.moduleDao.find(
                " from Module r where r.id<>? and  r.pid <>? and r.delFlag =? and r.isenable =? order by orderby asc,createTime desc", "2", "2", 0, 1);
    }

    @Override
    public void datagrid(Module module, DataGrid<Module> datagrid,
                         HttpServletRequest request) {
        this.moduleDao.datagrid(module, datagrid, request);
    }

    @Override
    public Module get(String id) {
        if (id == null)
            return new Module();
        return this.moduleDao.get(id);
    }

    @Override
    public ResultCode update(Module s) {
        if (s.getId() == null)
            return ResultCode.INVALID;
        this.moduleDao.update(s);
        return ResultCode.SUCCESS;

    }

    @Override
    public ResultCode add(Module module) {
        this.moduleDao.add(module);
        return ResultCode.SUCCESS;
    }

    @Override
    public void deleteAlllogic(String id) {
        if (id == null)
            return;
        String[] idArray = id.split(",");
        for (int i = 0; i < idArray.length; i++) {
            this.roleModuleService.delete(idArray[i], (short) 2);
        }
        this.moduleDao.deleteAlllogic(id);

    }

    @Override
    public void deletelogic(String id) {
        if (id == null)
            return;
        this.roleModuleService.delete(id, (short) 2);
        this.moduleDao.deleteAlllogic(id);

    }

    @Override
    public List<Module> get(String[] ids) {
        if (ids.length == 0)
            return new ArrayList<Module>();
        this.moduleDao.getBatch(ids);
        return null;
    }

}
