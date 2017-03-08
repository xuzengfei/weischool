package com.ws.service.student;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.student.StudentOpenId;

/**
 * 用户微信端登录绑定信息服务接口
 * Created by admin on 2017/2/15.
 */
public interface StudentOpenIdService {
    /**
     * 获得对象
     *
     * @param openId openId
     * @return
     */
    StudentOpenId get(String openId);

    /**
     * 保存对象
     *
     * @param openId openId
     * @param stNo   学生学号
     * @param cpId   校区ID
     * @return
     */
    ResultCode save(String openId, String stNo, String cpId);
}
