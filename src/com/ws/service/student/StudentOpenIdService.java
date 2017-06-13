package com.ws.service.student;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.student.StudentOpenId;

import java.util.List;

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
     * 获得对象
      * @param id 主键
     * @return
     */
    StudentOpenId getById(String id);

    /**
     * 保存对象
     *
     * @param openId openId
     * @param stNo   学生学号
     * @param cpId   校区ID
     * @return
     */
    ResultCode save(String openId, String stNo, String cpId);

    /**
     * 添加
     * @param openId openId
     * @param stId 学生ID
     * @param cpId 校区ID
     * @param ct 创建时间戳
     * @return  -1该学生已经被绑定 1--成功
     */
    int save(String openId,String stId,String cpId,Long ct);

    /**
     * 获得openId下所有的学生
     * @param openId
     * @return
     */
    List<StudentOpenId> getListByOpenId(String openId);
}
