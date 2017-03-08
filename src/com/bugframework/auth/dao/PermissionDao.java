package com.bugframework.auth.dao;

import java.util.List;
import com.bugframework.auth.pojo.RoleModule;

import com.bugframework.common.dao.BaseDao;

public interface PermissionDao extends BaseDao<RoleModule> {

	List<RoleModule> findPermissionByRole(String id);

	List<RoleModule> getleftMenu(String id);
}
