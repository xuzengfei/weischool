package com.ws.service.consumer;

import java.math.BigDecimal;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.consumer.Consumer;
import com.ws.pojo.coupon.Coupon;
import com.ws.service.consumer.dao.ConsDao;
import com.ws.service.coupon.CoupService;

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

	@Override
	public ResultCode add(Consumer cons, String coupId) {

		if (coupId != null && !"".equals(coupId)) {
			Coupon coupon = coupService.get(coupId);
			int amount = Integer.parseInt(cons.getAmount());
			int userRule = Integer.parseInt(coupon.getUseRule());
			cons.setAmount(amount - userRule < 0 ? "0" : ""
					+ (amount - userRule));
			Coupon coupon1 = new Coupon();
			coupon1.setId(coupon.getId());
			coupon1.setIsenable(0);
			coupService.edit(coupon1);
		}
		cons.setCreateTime(System.currentTimeMillis());
		cons.setCreateName(ResourceUtil.getUserSession().getUser().getName());
		this.consDao.add(cons);

		return ResultCode.SUCCESS;
	}

	@Override
	public ResultCode edit(Consumer cons) {
		return null;
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
