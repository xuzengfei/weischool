package com.ws.service.notice.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.notice.Notice;
import org.springframework.stereotype.Repository;

/**
 * 公告管理数据层接口实现类
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/17
 */
@Repository(value = "noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao {

}
