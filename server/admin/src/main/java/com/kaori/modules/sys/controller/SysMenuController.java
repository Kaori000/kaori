package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kaori.common.annotation.SysLog;
import com.kaori.common.utils.Constant;
import com.kaori.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaori.modules.sys.entity.SysMenuEntity;
import com.kaori.modules.sys.service.SysMenuService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 菜单管理
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:59
 */
@RestController
@RequestMapping("sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表
     */
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysMenuService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取菜单列表树形结构
     */
    @SysLog("列表")
    @GetMapping("/nav")
    @RequiresPermissions("sys:menu:nav")
    public R nav(){
        List<SysMenuEntity> data = sysMenuService.getMenuNav();
        List<SysMenuEntity> collect = data.stream().sorted(Comparator.comparing(SysMenuEntity::getOrderNum)).collect(Collectors.toList());
        return R.ok().put("data", collect);
    }

    @SysLog("列表")
    @GetMapping("/node")
    @RequiresPermissions("sys:menu:nav")
    public R node(){
        List<SysMenuEntity> data = sysMenuService.getMenuNav();
        List<SysMenuEntity> collect = data.stream().filter(f->f.getType()!= Constant.MenuType.BUTTON.getValue()).sorted(Comparator.comparing(SysMenuEntity::getOrderNum)).collect(Collectors.toList());
        return R.ok().put("data", collect);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("id") String id){
        SysMenuEntity sysMenu = sysMenuService.getById(id);

        return R.ok().put("sysMenu", sysMenu);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuEntity sysMenu){
        sysMenuService.save(sysMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenuEntity sysMenu){
        ValidatorUtils.validateEntity(sysMenu);
        sysMenuService.updateById(sysMenu);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody String[] ids){
        sysMenuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
