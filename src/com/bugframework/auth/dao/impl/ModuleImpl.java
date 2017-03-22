package com.bugframework.auth.dao.impl;


 

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.bugframework.auth.dao.ModuleDao;
import com.bugframework.auth.pojo.Module;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;

 

@Repository("moduleDao")
public class ModuleImpl extends BaseDaoImpl<Module> implements ModuleDao{

 

	 @Override
		public void datagrid(Module t, DataGrid<Module> datagrid,HttpServletRequest request) {
			Criteria cq =this.getCriteria();
			cq.add(Expression.eq("pid", request.getParameter("parentid")));
			cq.addOrder(Order.asc("orderby"));
			cq.addOrder(Order.desc("createTime"));
			new HqlGenerateUtil().installHql(cq, t,request);
			this.setDataGridData(cq,datagrid,true);
		}

	 
}
