package com.bugframework.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.UserAccountDao;
import com.bugframework.auth.dao.UserDao;
import com.bugframework.auth.pojo.User;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.auth.service.UserService;
import com.bugframework.common.pojo.DataGrid;

/**
 * 
 * <p>
 * Title:用户服务实现层
 * </p>
 * <p>
 * Description: 用户服务实现层
 * </p>
 * 
 * @author 许增飞
 * @date 2016-8-14 上午9:18:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserAccountDao userAccountDao;

	@Override
	public UserAccount findUser(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return userAccountDao.findUser(userAccount);
	}

	@Override
	public List<User> enableUser() {
		// TODO Auto-generated method stub
		return this.userDao.enableUser();
	}

	@Override
	public void update(User sysuser) {
		this.userDao.update(sysuser);
	}

	@Override
	public void datagrid(User user, DataGrid<User> datagrid,
			HttpServletRequest request) {
		this.userDao.datagrid(user, datagrid, request);
	}

	@Override
	public User get(String id) {
		if (id == null)
			return new User();
		return this.userDao.get(id);
	}

	@Override
	public void add(User sysUser) {
		this.userDao.add(sysUser);

	}

	@Override
	public void deleteAlllogic(String id) {
		if (id != null) {
			this.userDao.deleteAlllogic(id);
		}
	}

	@Override
	public void deletelogic(String id) {
		if (id != null) {
			this.userDao.deletelogic(id);
		}
	}

	@Override
	public List<User> getUser(String name, String value) {
		if (name == null)
			return null;
		return this.userDao.find(" from AuthUser r where r." + name
				+ "=? and delFlag=0", value);
	}

}
