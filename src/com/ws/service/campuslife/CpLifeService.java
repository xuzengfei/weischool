package com.ws.service.campuslife;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campuslife.CpLife;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 校区生活服务接口
 * Created by Administrator on 2017/3/9.
 */
public interface CpLifeService {
    /**
     * 保存一条记录
     * @param cpLife CpLife对象值
     * @return
     */
    ResultCode add(CpLife cpLife);

    /**
     * 更新对象
     *
     * @param cpLife CpLife对象值
     * @return
     */
    ResultCode edit(CpLife cpLife);

    /**
     * 获得校区生活
     *
     * @param id 主键
     * @return CpLife对象
     */
    CpLife get(String id);

    /**
     * 获得对象列表
     * @param cpLife 查询对象
     * @return
     */
    List<CpLife> list(CpLife cpLife);

    /**
     * 分页查询
     *
     * @param cpLife   校区对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(CpLife cpLife, DataGrid<CpLife> datagrid, HttpServletRequest request);
}
