package com.kaori.modules.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.user.dao.VeteransDao;
import com.kaori.modules.user.entity.VeteransEntity;
import com.kaori.modules.user.service.VeteransService;


@Service("veteransService")
public class VeteransServiceImpl extends ServiceImpl<VeteransDao, VeteransEntity> implements VeteransService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VeteransEntity> page = this.page(
                new Query<VeteransEntity>().getPage(params),
                new QueryWrapper<VeteransEntity>()
        );

        return new PageUtils(page);
    }

}
