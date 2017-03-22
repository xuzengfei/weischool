package com.bugframework.auth.dao;


import java.util.List;

import com.bugframework.auth.pojo.Role;
import com.bugframework.common.dao.BaseDao;
 

public interface RoleDao  extends BaseDao<Role>{
	/**
	 * 获得启用的角色
	 * @return
	 */
	List<Role> getRoleList(Role role);
}
