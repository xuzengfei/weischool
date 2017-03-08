package com.bugframework.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.AccountDao;
import com.bugframework.auth.dao.UserDao;
import com.bugframework.auth.pojo.User;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.auth.service.AccountService;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.ResultCode;

/**
 * 
 * <p>
 * Title:用户帐号服务实现层
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 许增飞
 * @date 2016-10-18 下午11:34:37
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	 
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private UserDao userDao;
	@Override
	public void datagrid(UserAccount account, DataGrid<UserAccount> datagrid,
			HttpServletRequest request) {
		
		this.accountDao.datagrid(account, datagrid, request);
	}

	@Override
	public ResultCode add(UserAccount account) {
		if (account == null || !account.isAddValid())
			return ResultCode.INVALID;
		List<UserAccount> accountList = this.accountDao.find(
				"from UserAccount where account =? ", account.getAccount());
		if (DataUtils.isListNotEmpty(accountList))
			return ResultCode.EXIST;
			User user = new User();
			user.setName(account.getUser().getName());
			this.userDao.add(user);
			account.setUser(user);
			this.accountDao.add(account);
		return ResultCode.SUCCESS;
	}

	@Override
	public ResultCode update(UserAccount account) {
		if (account.getId() == null)
			return ResultCode.INVALID;
			if (this.accountDao.update(account))
				return ResultCode.SUCCESS;
			else
				return ResultCode.FAIL;
	}

	@Override
	public UserAccount get(String id) {
		if (id == null)
			return null;
		return this.accountDao.get(id);
	}

	@Override
	public ResultCode edit(UserAccount account) {
		// TODO Auto-generated method stub	
		if (account.getId() == null)
			return ResultCode.INVALID;
		 
			if (this.accountDao.update(account))
				return ResultCode.SUCCESS;
			else
				return ResultCode.FAIL;
	}

}
