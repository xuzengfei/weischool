package com.bugframework.auth.service;

import com.bugframework.auth.pojo.RoleModule;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */
public interface RoleModuleService {
    /**
     * 通过角色ID获得权限列表
     *
     * @param id 传入角色ID
     * @return List<AuthPermisstion>
     */
    List<RoleModule> findByRole(String id);

    /**
     * 批量添加
     *
     * @param roleModules 添加的数据
     * @param role        先通过role删除旧的数据
     */
    void add(List<RoleModule> roleModules, String role);

    /**
     * 物理删除
     *
     * @param id     传入删除的ID，此ID类型根据status参数指定
     * @param status 0--传入的id是权限的主键 1--传入id是角色ID 2--传入的id是模块ID
     */
    void delete(String id, short status);
}
