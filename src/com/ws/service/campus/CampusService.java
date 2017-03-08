package com.ws.service.campus;


import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campus.Campus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CampusService {
    /**
     * 通过主键获取校区
     *
     * @param id 校区ID
     * @return
     */
    Campus get(String id);

    /**
     * 分页查询
     *
     * @param campus   校区对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Campus campus, DataGrid<Campus> datagrid, HttpServletRequest request);

    /**
     * 添加操作
     *
     * @param campus 校区对象
     * @return ResultCode
     */
    ResultCode add(Campus campus);

    /**
     * 更新
     *
     * @param campus           校区值，ID不能为空
     * @param isNameExistValid 是否校区名称是否存在验证
     * @return ResultCode
     */
    ResultCode update(Campus campus, boolean isNameExistValid);

    /**
     * 获得列表
     *
     * @param campus 传入查询对象
     * @return
     */
    List<Campus> find(Campus campus);

}
