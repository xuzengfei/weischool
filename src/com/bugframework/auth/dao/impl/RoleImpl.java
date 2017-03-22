package com.bugframework.auth.dao.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.Role;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;

 

@Repository("roleDao")
public class RoleImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public List<Role> getRoleList(Role role) {
		 Criteria criteria=getSession().createCriteria(Role.class);
		 if(role.getIsenable()!=null)
		     criteria.add(Expression.eq("isenable", role.getIsenable()));
	 	/* 
		 if(role.getIsAdmin()!=null)
			 criteria.add(Expression.eq("isAdmin", role.getIsAdmin()));
		 if(role.getDelFlag()!=null)
			 criteria.add(Expression.eq("delFlag", role.getDelFlag()));*/
		 criteria.addOrder(Order.asc("orderby")); 
		 List<Role> deptList=criteria.list();  
		return deptList;
	}

	 @Override
		public void datagrid(Role t, DataGrid<Role> datagrid,HttpServletRequest request) {
			Criteria cq =this.getCriteria();
			new HqlGenerateUtil().installHql(cq, t, request);
			this.setDataGridData(cq,datagrid,true);
		}

}
