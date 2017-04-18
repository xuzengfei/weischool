package com.bugframework.auth.service;

import com.bugframework.auth.pojo.Role;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title:角色服务接口层 </p>
 * <p>Description: 角色服务接口层</p>
 *
 * @author 许增飞
 * @date 2016-8-14 上午10:27:05
 */
public interface RoleService {
    /**
     * 获得启用的角色
     *
     * @return
     */
    List<Role> getRoleList();

    /**
     * 通过主键获取角色
     *
     * @param id 角色ID
     * @return
     */
    Role get(String id);

    /**
     * 分页查询
     *
     * @param role     角色对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Role role, DataGrid<Role> datagrid, HttpServletRequest request);

    /**
     * 更新
     *
     * @param s                对象值，ID不能为空
     * @param isNameExistValid 是否角色名称是否存在验证
     * @param moduels          模块IDs,可以为null
     * @return ResultCode
     */
    ResultCode update(Role s, boolean isNameExistValid, String[] moduels);

    /**
     * 更新
     *
     * @param s                对象值，ID不能为空
     * @param isNameExistValid 是否角色名称是否存在验证
     * @return ResultCode
     */
    ResultCode update(Role s, boolean isNameExistValid);

    /**
     * 批量删除角色
     *
     * @param id 角色ID，逗号隔开
     */
    void deleteAlllogic(String id);

    /**
     * 删除，物理删除<br>
     * <p>
     * 1.校验是否已经被分配用户，如果被分配用户，则不给删除
     * </p>
     * <p>
     * 2.删除成功要把权限给删除
     * </p>
     *
     * @param id 角色ID
     */
    ResultCode delete(String id);

    /**
     * 添加操作
     *
     * @param role    角色对象
     * @param moduels 模块IDs
     * @return ResultCode
     */
    ResultCode add(Role role, String[] moduels);

    /**
     * 通过名字查询
     *
     * @param param
     * @return
     */
    List<Role> findByName(String param);

}
