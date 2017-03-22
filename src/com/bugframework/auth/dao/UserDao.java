package com.bugframework.auth.dao;

import java.util.List;


import com.bugframework.auth.pojo.User;
import com.bugframework.common.dao.BaseDao;

public interface UserDao extends BaseDao<User> {
	
	/**
	 * 获取可用的用户
	 * 
	 * @return
	 */
	public List<User> enableUser();

}
