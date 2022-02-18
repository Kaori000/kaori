package com.kaori.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaori.common.utils.PageUtils;
import com.kaori.modules.sys.entity.SysRoleEntity;

import java.util.Map;

/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:59
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveRole(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(String[] roleIds);



}

