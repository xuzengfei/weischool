package com.ws.service.attach;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.attach.Attach;

import java.util.List;

/**
 * Created by 许增飞 on 2017/1/13.
 */
public interface AttachService {
    //保存
    ResultCode save(Attach attach);

    /**
     * 修改附件表数据
     * ID不能为空
     *
     * @param attach 传入对象
     * @return
     */
    ResultCode edit(Attach attach);

    /**
     * 批量更新附件的模块ID
     * @param ids 附件ID数组
     * @param moduleId 模块ID
     */
    void edit(String[] ids, String moduleId);

    /**
     * 通过模块ID更新删除状态
     * @param moduleId 模块ID
     * @param delFlag 删除或者非删除
     */
    void edit(String moduleId, int delFlag);

    //获取列表
    List<Attach> list(Attach attach);

    /**
     * 删除(删除服务器硬盘上的文件后再删除数据库记录的数据)
     *
     * @param id 主键ID
     * @return ResultCode
     */
    ResultCode del(String id);

    /**
     * 获得路径
     * @param moduleId 模块ID
     * @return
     */
    List<String> listPath(String moduleId);


}
