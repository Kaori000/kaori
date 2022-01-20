package com.kaori.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.Query;
import com.kaori.modules.sys.dao.SysMenuDao;
import com.kaori.modules.sys.entity.SysMenuEntity;
import com.kaori.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    @Autowired
    SysMenuDao sysMenuDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysMenuEntity> page = this.page(
                new Query<SysMenuEntity>().getPage(params),
                new QueryWrapper<SysMenuEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<SysMenuEntity> getMenuList() {
        return sysMenuDao.getAllMenu();
    }


    @Override
    public List<SysMenuEntity> getMenu() {
        List<SysMenuEntity> allMenu = sysMenuDao.getAllMenu();
        List<SysMenuEntity> collect = allMenu.stream().filter(f -> f.getParentId().equals("0")).map((m) -> {
            m.setChildren(getChildren(allMenu, m));
            return m;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 获取子节点
     */
    public List<SysMenuEntity> getChildren(List<SysMenuEntity> list,SysMenuEntity root){
        List<SysMenuEntity> collect = list.stream().filter(f -> f.getParentId().equals(root.getId())).map((m) -> {
            m.setChildren(getChildren(list, m));
            return m;
        }).collect(Collectors.toList());
        return collect;
    }

}
