package com.ws.service.coupon;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.coupon.Coupon;
import com.ws.service.coupon.dao.CoupDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
@Service(value = "coupSerivce")
public class CoupServiceImpl implements CoupService {
    @Autowired
    private CoupDao coupDao;

    @Override
    public Coupon get(String id) {
        if (id == null)
            return null;
        return this.coupDao.get(id);
    }

    @Override
    public List<Coupon> listByStudent(String stId) {
        if (stId == null)
            return null;
        List<Coupon> list = this.coupDao.find("from Coupon c where  c.student.id=? and c.isenable=? and c.delFlag=? ", stId, 1, 0);
        Iterator<Coupon> iter = list.iterator();
        while (iter.hasNext()) {
            Coupon coupon = iter.next();
            if (coupon.getEd() < System.currentTimeMillis() && coupon.getSt() > System.currentTimeMillis()) {
                iter.remove();
            }
        }
        return list;
    }

    @Override
    public List<Coupon> list(Coupon coupon) {
        Criteria cq = this.coupDao.getSession().createCriteria(Coupon.class);
        if (coupon.getStId() != null) {
            cq.createAlias("student", "st");
            cq.add(Expression.eq("st.id", coupon.getStId()));
        }
        if (coupon.getIsenable() != null)
            cq.add(Expression.eq("isenable", coupon.getIsenable()));
        if (coupon.getNo() != null)
            cq.add(Expression.eq("no", coupon.getNo()));
        cq.add(Expression.eq("delFlag", 0));
        cq.addOrder(Order.desc("st"));
        return cq.list();
    }

    @Override
    public ResultCode edit(Coupon coupon) {
        if (coupon == null)
            return ResultCode.INVALID;
        this.coupDao.update(coupon);
        return ResultCode.SUCCESS;

    }

    @Override
    public void datagrid(Coupon coupon, DataGrid<Coupon> datagrid, HttpServletRequest request) {
        coupDao.datagrid(coupon, datagrid, request);
    }

    @Override
    public ResultCode add(Coupon coupon) {
        if (!coupon.isAddIlegal())
            return ResultCode.INVALID;
        coupDao.add(coupon);
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode batchAdd(List<Coupon> coupon) {
        coupDao.batchAdd(coupon);
        return ResultCode.SUCCESS;
    }
}
