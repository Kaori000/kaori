package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kaori.common.annotation.SysLog;
import com.kaori.common.utils.Constant;
import com.kaori.common.utils.MessageCode;
import com.kaori.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags="系统菜单")
@RestController
@RequestMapping("sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表
     */
    @SysLog("列表")
    @ApiOperation(value="获取菜单列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysMenuService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取菜单列表树形结构
     */
    @SysLog("获取导航菜单")
    @ApiOperation(value="获取导航菜单")
    @GetMapping("/nav")
    @RequiresPermissions("sys:menu:nav")
    public R nav(){
        List<SysMenuEntity> data = sysMenuService.getMenuNav();
        List<SysMenuEntity> collect = data.stream().sorted(Comparator.comparing(SysMenuEntity::getOrderNum)).collect(Collectors.toList());
        return R.ok().put("data", collect);
    }

    @SysLog("列表")
    @ApiOperation(value="获取导航菜单（非按钮）")
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
    @ApiOperation(value="获取菜单信息")
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
    @ApiOperation(value="添加菜单")
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
    @ApiOperation(value="修改菜单")
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
    @ApiOperation(value="删除菜单")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody String[] ids){
        for (int i = 0; i < ids.length; i++) {
            String menuId = ids[i];
            //判断是否有子菜单或按钮
            List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
            if(menuList.size() > 0){
                return R.error(MessageCode.MENU_OPERATION_FIRST_DELETE_PREVIOUS_MENU.getCode(), "请先删除子菜单或按钮");
            }
            sysMenuService.delete(menuId);
        }

        return R.ok();
    }

}
