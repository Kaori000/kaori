package com.kaori.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;
import com.kaori.common.utils.StringUtil;
import com.kaori.modules.sys.dao.SysUserDao;
import com.kaori.modules.sys.entity.SysUserEntity;
import com.kaori.modules.sys.service.SysUserService;
import com.kaori.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper queryWrapper = new QueryWrapper<SysUserEntity>();
        if(!StringUtil.isEmpty(params.get("username"))){
            queryWrapper.eq("username",params.get("username"));
        }
        if(!StringUtil.isEmpty(params.get("status"))){
            queryWrapper.eq("status",params.get("status"));
        }
        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void saveUser(SysUserEntity sysUserEntity) {
        sysUserEntity.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUserEntity.setCreateTime(new Date());
        sysUserEntity.setSalt(salt);
        sysUserEntity.setPassword(ShiroUtils.sha256(sysUserEntity.getPassword(), sysUserEntity.getSalt()));
        save(sysUserEntity);
    }

}
