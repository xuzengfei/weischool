package com.ws.service.campuslife;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campuslife.CpLife;
import com.ws.service.attach.AttachService;
import com.ws.service.campuslife.dao.CpLifeDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 校园生活服务实现层
 * Created by Administrator on 2017/3/9.
 */
@Service
public class CpLifeServiceImpl implements CpLifeService {
    @Autowired
    private CpLifeDao cpLifeDao;
    @Autowired
    private AttachService attachService;

    @Override
    public ResultCode add(CpLife cpLife) {
        this.cpLifeDao.add(cpLife);
        String[] pic = new String[1];
        if(cpLife.getPic()!=null){
            pic[0] =cpLife.getPic();
            this.attachService.edit(pic, cpLife.getId());
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode edit(CpLife cpLife) {
        if (cpLife.getId() == null)
            return ResultCode.INVALID;
        this.cpLifeDao.update(cpLife);
        return ResultCode.SUCCESS;
    }

    @Override
    public CpLife get(String id) {
        if (id == null)
            return null;
        return this.cpLifeDao.get(id);
    }

    @Override
    public List<CpLife> list(CpLife cpLife) {
        Criteria cq  =  this.cpLifeDao.getSession().createCriteria(CpLife.class);
        if (cpLife.getCpId()!=null)
            cq.add(Expression.eq("cpId",cpLife.getCpId()));
        if (cpLife.getIsenable()!=null)
            cq.add(Expression.eq("isenable",cpLife.getIsenable()));
        cq.addOrder(Order.desc("st"));

        return cq.list();
    }

    @Override
    public void datagrid(CpLife cpLife, DataGrid<CpLife> datagrid, HttpServletRequest request) {
        List<String[]> orders = new ArrayList<String[]>();
        orders.add(new String[]{"st", "desc"});
        datagrid.setOrder(orders);
        this.cpLifeDao.datagrid(cpLife, datagrid, request);
    }
}
