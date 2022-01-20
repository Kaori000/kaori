package com.kaori.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.sys.dao.SysLoginLogDao;
import com.kaori.modules.sys.entity.SysLoginLogEntity;
import com.kaori.modules.sys.service.SysLoginLogService;


@Service("sysLoginLogService")
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogDao, SysLoginLogEntity> implements SysLoginLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysLoginLogEntity> page = this.page(
                new Query<SysLoginLogEntity>().getPage(params),
                new QueryWrapper<SysLoginLogEntity>()
        );

        return new PageUtils(page);
    }

}
