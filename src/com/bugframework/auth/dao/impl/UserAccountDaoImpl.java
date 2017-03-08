package com.bugframework.auth.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import com.bugframework.auth.dao.UserAccountDao;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.utility.DataUtils;
@Repository(value = "userAccountDao")
public class UserAccountDaoImpl extends BaseDaoImpl<UserAccount> implements UserAccountDao{


	@Override
	public UserAccount findUser(UserAccount user) {
		if (user != null && !DataUtils.isStrEmpty(user.getAccount())
				&& !DataUtils.isStrEmpty(user.getPassword())) {
			Criteria cq = this.getSession().createCriteria(UserAccount.class);
			cq.add(Expression.eq("account", user.getAccount()));
			// cq.add(Expression.eq("password", user.getPassword()));
			cq.add(Expression.eq("isenable", 1));
			List list = cq.list();
			if (!DataUtils.isListEmpty(list)) {// 先取出來再比較密碼 會更加安全，防止sql注入
				UserAccount s = (UserAccount) list.get(0);
				if (s.getPassword().equals(user.getPassword())) {
					return s;
				} else {
					return null;
				}
			}
		}
		return null;
	}
}
