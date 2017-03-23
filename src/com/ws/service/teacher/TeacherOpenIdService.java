package com.ws.service.teacher;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.teacher.TeacherOpenId;

/**
 * 老师微信端登录绑定信息服务接口
 * Created by admin on 2017/2/15.
 */
public interface TeacherOpenIdService {
    /**
     * 获得对象
     *
     * @param openId openId
     * @return
     */
    TeacherOpenId get(String openId);

    /**
     * 保存对象
     *
     * @param openId openId
     * @param tcNo   老师工号
     * @return
     */
    ResultCode save(String openId, String tcNo);
}
