package com.bugframework.auth.service;

import com.bugframework.auth.dao.RoleModuleDao;
import com.bugframework.auth.pojo.RoleModule;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */
@Service
public class RoleModuleServiceImpl implements RoleModuleService {
    @Autowired
    private RoleModuleDao dao;

    @Override
    public List<RoleModule> findByRole(String id) {
        Criteria cq = this.dao.getSession().createCriteria(RoleModule.class);
        cq.add(Expression.eq("role",id));
        cq.createAlias("module","m");
        cq.add(Expression.eq("m.isenable",1));
        cq.add(Expression.eq("m.delFlag",0));
        cq.addOrder(Order.asc("m.orderby"));
        cq.addOrder(Order.desc("m.createTime"));
        return cq.list();
    }

    @Override
    public void add(List<RoleModule> roleModules, String role) {
        this.dao.delete("role", role);
        this.dao.batchAdd(roleModules);
    }

    @Override
    public void delete(String id, short status) {
        if (id == null)
            return;
        switch (status) {
            case 0:
                this.dao.delete(id);
                break;
            case 1:
                this.dao.delete("role", id);
                break;
            case 2:
                this.dao.delete("module.id", id);
                break;
        }
    }
}
