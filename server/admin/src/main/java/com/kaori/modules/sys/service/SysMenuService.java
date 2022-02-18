package com.kaori.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaori.common.utils.PageUtils;
import com.kaori.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2021-11-04 21:16:05
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysMenuEntity> getMenuList();

    List<SysMenuEntity> getMenuNav();

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(String parentId);


    void delete(String menuId);
}

