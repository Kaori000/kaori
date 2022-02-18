package com.kaori.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.sys.dao.SysDictDao;
import com.kaori.modules.sys.entity.SysDictEntity;
import com.kaori.modules.sys.service.SysDictService;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDictEntity> page = this.page(
                new Query<SysDictEntity>().getPage(params),
                new QueryWrapper<SysDictEntity>().eq("type","0")
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysDictEntity> getByType(String type) {
        QueryWrapper queryWrapper = new QueryWrapper<SysDictEntity>().eq("type",type);
        List<SysDictEntity> data = list(queryWrapper);
        return data;
    }

}
