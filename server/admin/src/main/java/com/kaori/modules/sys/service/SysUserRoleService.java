package com.kaori.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaori.common.utils.PageUtils;
import com.kaori.modules.sys.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:58
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    void saveOrUpdate(String userId, List<String> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<String> queryRoleIdList(String userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(String[] roleIds);
}

