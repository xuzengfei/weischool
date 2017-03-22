package com.ws.service.notice;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.notice.Notice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公告管理服务层接口
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/17
 */
public interface NoticeService {
    /**
     * 发布公告
     *
     * @param notice 公告信息
     * @return 返回ResultCode封装的发布公告操作的状态
     */
    ResultCode add(Notice notice);

    /**
     * 分页查询公告
     *
     * @param notice   公告信息
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Notice notice, DataGrid<Notice> datagrid, HttpServletRequest request);

    /**
     * 通过公告主键获得公告详情
     *
     * @param id 公告id
     * @return 返回Notice公告详情
     */
    Notice get(String id);

    /**
     * 更新公告
     *
     * @param notice 公告信息
     * @return 返回ResultCode封装的更新公告操作状态信息
     */
    ResultCode update(Notice notice);

    /**
     * 获得公告列表
     * @param page 第几页
     * @return
     */
    List<Notice> find(Integer page);
}
