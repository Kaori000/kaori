package com.kaori.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaori.common.utils.PageUtils;
import com.kaori.modules.sys.entity.SysUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2021-11-04 21:16:05
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void saveUser(SysUserEntity sysUserEntity);
}

