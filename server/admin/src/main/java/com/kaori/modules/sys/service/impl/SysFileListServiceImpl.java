package com.kaori.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.sys.dao.SysFileListDao;
import com.kaori.modules.sys.entity.SysFileListEntity;
import com.kaori.modules.sys.service.SysFileListService;


@Service("sysFileListService")
public class SysFileListServiceImpl extends ServiceImpl<SysFileListDao, SysFileListEntity> implements SysFileListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysFileListEntity> page = this.page(
                new Query<SysFileListEntity>().getPage(params),
                new QueryWrapper<SysFileListEntity>()
        );

        return new PageUtils(page);
    }

}
