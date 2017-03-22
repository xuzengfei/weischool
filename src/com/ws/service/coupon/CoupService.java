package com.ws.service.coupon;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.coupon.Coupon;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 优惠/代金券服务接口
 *
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
public interface CoupService {
    /**
     * 通过主键获取对象
     *
     * @param id 主键
     * @return
     */
    Coupon get(String id);

    /**
     * 通过学生ID获得启用的列表
     *
     * @param stId 学生ID
     * @return
     */
    List<Coupon> listByStudent(String stId);


    /**
     * 修改
     *
     * @param coupon
     */
    ResultCode edit(Coupon coupon);

    /**
     * 分页
     *
     * @param coupon   查询对象
     * @param datagrid 分页模型
     * @param request
     */
    void datagrid(Coupon coupon, DataGrid<Coupon> datagrid, HttpServletRequest request);
    /**
     * 添加
     * @param coupon
     * @return
     */
    ResultCode add(Coupon coupon);
    /**
     * 批量添加
     * @param coupon
     * @return
     */
    ResultCode batchAdd(List<Coupon> coupon);

    /**
     * 获得列表
     * @param coupon 传入查询对象:stId
     * @return
     */
    List<Coupon> list(Coupon coupon);
}
