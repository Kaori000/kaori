package com.kaori.modules.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.user.dao.VeteransAwardDao;
import com.kaori.modules.user.entity.VeteransAwardEntity;
import com.kaori.modules.user.service.VeteransAwardService;


@Service("veteransAwardService")
public class VeteransAwardServiceImpl extends ServiceImpl<VeteransAwardDao, VeteransAwardEntity> implements VeteransAwardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VeteransAwardEntity> page = this.page(
                new Query<VeteransAwardEntity>().getPage(params),
                new QueryWrapper<VeteransAwardEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void removeAwards(String id){
        QueryWrapper<VeteransAwardEntity> queryWrapper = new QueryWrapper<>();
        this.remove(queryWrapper.eq("veteransId",id));
    }


}
