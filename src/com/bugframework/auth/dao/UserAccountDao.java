package com.bugframework.auth.dao;
 
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.dao.BaseDao;

public interface UserAccountDao extends BaseDao<UserAccount>{
	/**
	 * 查找用户，通过帐号和密码
	 * 
	 * @param user
	 * @return
	 */
	public UserAccount findUser(UserAccount user);
}
