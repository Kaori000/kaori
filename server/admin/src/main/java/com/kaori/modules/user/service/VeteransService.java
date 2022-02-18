package com.kaori.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaori.common.utils.PageUtils;
import com.kaori.modules.user.entity.VeteransEntity;

import java.util.Map;

/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-02-18 11:06:43
 */
public interface VeteransService extends IService<VeteransEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

