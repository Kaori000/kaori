package com.kaori.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaori.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author lfh
 * @email kaoriii@163.com
 * @date 2021-11-04 21:16:05
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(String userId);
}
