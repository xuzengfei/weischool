package com.bugframework.auth.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bugframework.auth.dao.UserDao;
import com.bugframework.auth.pojo.User;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.HqlGenerateUtil;
/**
 * 
 * <p>Title:UserDaoImpl </p>
 * <p>Description: 用户数据持久层</p>
 * @author 许增飞
 * @date 上午8:49:36
 */
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


	@Override
	public void datagrid(User t, DataGrid<User> datagrid,
			HttpServletRequest request) {
		Criteria cq = this.getCriteria();
		/*if (DataUtils.isStrNotEmpty(t.getRoleName())) {// 角色名称不属于AuthUser表里面的，所以要做特殊处理
			cq.createAlias("sysRole", "role");// sysRole是AuthUser里面的一个字段，role是我取的别名，可以任意取，这句话主要是为角色表其一个别名
			cq.add(Expression.like("role.name", "%" + t.getRoleName() + "%"));// role.name==sysRole.name，相当于是AuthRole中的name字段.模糊查询
		}*/
		cq.addOrder(Order.desc("createTime"));
		new HqlGenerateUtil().installHql(cq, t,request);
		this.setDataGridData(cq, datagrid, true);
	}

	@Override
	public List<User> enableUser() {
		Criteria cq = this.getCriteria();
		cq.add(Expression.eq("isenable", 1));
		cq.add(Expression.eq("delFlag", 0));
		return cq.list();
	}

}
