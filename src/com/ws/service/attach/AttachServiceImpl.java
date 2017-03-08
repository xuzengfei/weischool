package com.ws.service.attach;

import com.bugframework.common.utility.BaseUpload;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.attach.Attach;
import com.ws.service.attach.dao.AttachDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
@Service(value = "attachService")
public class AttachServiceImpl implements AttachService {
    @Autowired
    private AttachDao attachDao;

    @Override
    public ResultCode save(Attach attach) {
        if (!attach.isAddValid())
            return ResultCode.INVALID;
        this.attachDao.add(attach);
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode edit(Attach attach) {
        if (!attach.isUpdValid())
            return ResultCode.INVALID;
        this.attachDao.update(attach);
        return ResultCode.SUCCESS;
    }

    @Override
    public void edit(String[] ids, String moduleId) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                Attach attach = new Attach();
                attach.setModuleId(moduleId);
                attach.setId(id);
                this.attachDao.update(attach);
            }

        }

    }

    @Override
    public void edit(String moduleId, int delFlag) {
        if (moduleId == null)
            return;
        this.attachDao.batchExecute("update Attach a set a.delFlag =? where a.moduleId =?",delFlag,moduleId);
    }

    @Override
    public List<Attach> list(Attach attach) {
        Criteria criteria = this.attachDao.getSession().createCriteria(Attach.class);
        criteria.add(Expression.eq("moduleId", attach.getModuleId()));
        criteria.add(Expression.eq("delFlag",0));
        criteria.addOrder(Order.asc("createTime"));
        return criteria.list();
    }

    @Override
    public ResultCode del(String id) {
        if (id == null)
            return ResultCode.INVALID;
        new BaseUpload().del(this.attachDao.get(id).getPath());
        this.attachDao.delete(id);
        return ResultCode.SUCCESS;
    }

    @Override
    public List<String> listPath(String moduleId) {
        if(moduleId==null)
            return null;
        Query query = this.attachDao.getSession().createQuery("select path from Attach where moduleId=? and delFlag =0 order by createTime asc");
        query.setParameter(0, moduleId);
        return query.list();
    }
}
