package com.bugframework.auth.dao.impl;
 
import org.springframework.stereotype.Repository;

import com.bugframework.auth.dao.AccountDao;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.dao.impl.BaseDaoImpl;
 
/**
 * 
 * <p>Title:用户帐号数据持久层 </p>
 * <p>Description: </p>
 * @author 许增飞
 * @date 2016-10-18 下午11:32:33
 */
@Repository(value = "accountDao")
public class AccountDaoImpl extends BaseDaoImpl<UserAccount> implements AccountDao{

	 
}
