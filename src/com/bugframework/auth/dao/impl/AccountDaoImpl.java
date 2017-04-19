package com.bugframework.auth.dao.impl;

import com.bugframework.auth.dao.AccountDao;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title:用户帐号数据持久层 </p>
 * <p>Description: </p>
 *
 * @author 许增飞
 * @date 2016-10-18 下午11:32:33
 */
@Repository(value = "accountDao")
public class AccountDaoImpl extends BaseDaoImpl<UserAccount> implements AccountDao {

    @Override
    public void datagrid(UserAccount userAccount, DataGrid<UserAccount> datagrid, HttpServletRequest request) {
        Criteria cq = this.getCriteria();
        cq.add(Expression.ne("id", "1"));
        new HqlGenerateUtil().installHql(cq, userAccount, request);
        this.setDataGridData(cq, datagrid, datagrid.isPaging());


    }
}
