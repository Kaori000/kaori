package com.kaori.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaori.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author lfh
 * @email kaoriii@163.com
 * @date 2021-11-04 21:16:05
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
    @Select("select * from sys_menu")
    public List<SysMenuEntity> getAllMenu();

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(String parentId);

}
