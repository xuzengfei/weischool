package com.bugframework.auth.dao.impl;


import com.bugframework.auth.dao.PermissionDao;
import com.bugframework.auth.pojo.RoleModule;
import com.bugframework.common.dao.impl.BaseDaoImpl1;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("permissionDao")
public class PermissionImpl extends BaseDaoImpl1<RoleModule> implements PermissionDao {

    @Override
    public List<RoleModule> findPermissionByRole(String id) {
        Criteria cq = this.getCriteria();
        cq.createAlias("role", "r");
        cq.add(Expression.eq("r.id", id));
        cq.createAlias("module", "m");
        cq.add(Expression.eq("m.isenable", 1));
        cq.add(Expression.eq("m.delFlag", 0));
        return cq.list();
    }


    @Override
    public List<RoleModule> getleftMenu(String id) {
        Criteria cq = this.getCriteria();
        cq.createAlias("role", "r");
        cq.add(Expression.eq("r.id", id));
        cq.createAlias("module", "m");
        cq.add(Expression.eq("m.isenable", 1));
        cq.add(Expression.eq("m.delFlag", 0));
        cq.addOrder(Order.asc("m.orderby"));
        cq.addOrder(Order.desc("m.createTime"));
        return cq.list();
    }


}
