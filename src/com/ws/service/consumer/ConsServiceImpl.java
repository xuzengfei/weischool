package com.ws.service.consumer;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.consumer.Consumer;
import com.ws.pojo.coupon.Coupon;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.consumer.dao.ConsDao;
import com.ws.service.coupon.CoupService;
import com.ws.service.student.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
@Service(value = "consService")
public class ConsServiceImpl implements ConsService {
    @Autowired
    private ConsDao consDao;
    @Autowired
    private CoupService coupService;
    @Autowired
    private StudentGradeService studentGradeService;

    @Override
    public ResultCode add(Consumer cons, String coupId) {
        System.out.println("cons:"+cons);
        System.out.println("coupId:"+coupId);
        if (coupId != null && !"".equals(coupId)) {
            Coupon coupon = coupService.get(coupId);
            cons.setRemark(coupon.getRemark());
            int amount = Integer.parseInt(cons.getAmount());
            int userRule = Integer.parseInt(coupon.getUseRule());
            cons.setAmount(amount - userRule < 0 ? "0" : ""
                    + (amount - userRule));
            Coupon coupon1 = new Coupon();
            coupon1.setId(coupon.getId());
            coupon1.setIsenable(1);
            coupService.edit(coupon1);
        }
        /*cons.setCreateTime(System.currentTimeMillis());
		cons.setCreateName(ResourceUtil.getUserSession().getUser().getName());*/
        this.consDao.add(cons);

        if (cons.getProjectType() == 1) {//更新课时
            String stId = cons.getStId();
            StudentGrade sGrade = this.studentGradeService.get(cons.getStId());
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setId(sGrade.getId());
            studentGrade.setRestClass(sGrade.getRestClass() + cons.getClassNum());
            this.studentGradeService.edit(studentGrade, false);
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode edit(Consumer cons) {
        if (null==cons.getId()||"".equals(cons.getId()))
            return ResultCode.INVALID;
        this.consDao.update(cons);
        return ResultCode.SUCCESS;
    }

    @Override
    public Consumer get(String id) {
        if (id == null)
            return null;
        return this.consDao.get(id);
    }

    @Override
    public void datagrid(Consumer cons, DataGrid<Consumer> datagrid,
                         HttpServletRequest request) {
        this.consDao.datagrid(cons, datagrid, request);
    }

    public static void main(String[] args) {
        String amount1 = Math.round(88 * Double.valueOf(0.95))
                + "";
        System.out.println(amount1);
    }
}
