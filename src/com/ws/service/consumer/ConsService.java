package com.ws.service.consumer;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.consumer.Consumer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
public interface ConsService {
    /**
     * 添加缴费记录[缴费清单]，费用计算：总额-代金券。
     * 保存缴费记录后，判断是否课时费用，如果是课时费用，要更新学生的最新课时
     * @param cons  缴费记录对象值
     * @param coupId 学生代金券ID,ID为空则不需要减去代金券
     * @return
     */
    ResultCode add(Consumer cons, String coupId);

    /**
     * 更新
     *
     * @param cons 更新对象
     * @return ResultCode
     */
    ResultCode edit(Consumer cons);

    /**
     * 获得对象
     *
     * @param id 传入主键
     * @return Experience值
     */
    Consumer get(String id);

    /**
     * 分页查询
     *
     * @param cons     荣誉证书对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Consumer cons, DataGrid<Consumer> datagrid, HttpServletRequest request);
}
