package com.kaori.modules.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;

import com.kaori.modules.user.dao.JobDao;
import com.kaori.modules.user.entity.JobEntity;
import com.kaori.modules.user.service.JobService;


@Service("jobService")
public class JobServiceImpl extends ServiceImpl<JobDao, JobEntity> implements JobService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<JobEntity> page = this.page(
                new Query<JobEntity>().getPage(params),
                new QueryWrapper<JobEntity>()
        );

        return new PageUtils(page);
    }

}
