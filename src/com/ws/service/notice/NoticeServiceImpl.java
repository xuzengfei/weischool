package com.ws.service.notice;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.notice.Notice;
import com.ws.service.attach.AttachService;
import com.ws.service.notice.dao.NoticeDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 公告管理服务层接口实现类
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/17
 */
@Service(value = "noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private AttachService attachService;
    /**
     * 发布公告
     *
     * @param notice 公告信息
     * @return 返回ResultCode封装的发布公告操作的状态
     */
    @Override
    public ResultCode add(Notice notice) {
        if(notice.getTitle()==null||"".equals(notice.getTitle())||notice.getContent()==null||"".equals(notice.getContent())){
            return ResultCode.INVALID;
        }
        this.noticeDao.add(notice);
        String[] pic = new String[1];
        if(notice.getPic()!=null){
            pic[0] =notice.getPic();
            this.attachService.edit(pic, notice.getId());
        }
        return ResultCode.SUCCESS;
    }

    /**
     * 分页查询公告
     *
     * @param notice   公告信息
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    @Override
    public void datagrid(Notice notice, DataGrid<Notice> datagrid, HttpServletRequest request) {
        List<String[]> orders = new ArrayList<String[]>();
        orders.add(new String[] { "createTime", "desc" });
        datagrid.setOrder(orders);
        this.noticeDao.datagrid(notice, datagrid, request);
    }

    /**
     * 通过公告主键获得公告详情
     *
     * @param id 公告id
     * @return 返回Notice公告详情
     */
    @Override
    public Notice get(String id) {
        return noticeDao.get(id);
    }

    /**
     * 更新公告
     *
     * @param notice 公告信息
     * @return 返回ResultCode封装的更新公告操作状态信息
     */
    @Override
    public ResultCode update(Notice notice) {
        if(notice.getId()==null||"".equals(notice.getId())){
            return ResultCode.INVALID;
        }
        noticeDao.update(notice);
        return ResultCode.SUCCESS ;
    }

    @Override
    public List<Notice> find(Integer page) {
        Map<String,Object> params = new HashedMap();
        params.put("isenable",1);
        params.put("delFlag",0);
        return this.noticeDao.findByPager(page,10,"createTime",true,params).getDatas();
    }
}
