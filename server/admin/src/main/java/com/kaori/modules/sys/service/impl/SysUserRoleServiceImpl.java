package com.kaori.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.sys.dao.SysUserRoleDao;
import com.kaori.modules.sys.entity.SysUserRoleEntity;
import com.kaori.modules.sys.service.SysUserRoleService;


@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {



    @Override
    public List<String> queryRoleIdList(String userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public void saveOrUpdate(String userId, List<String> roleIdList) {
        //先删除用户与角色关系
        this.remove(new QueryWrapper<SysUserRoleEntity>().eq("user_id", userId));

        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }

        //保存用户与角色关系
        for(String roleId : roleIdList){
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);

            this.save(sysUserRoleEntity);
        }

    }

    @Override
    public int deleteBatch(String[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }
}
