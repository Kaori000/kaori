package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kaori.common.annotation.SysLog;
import com.kaori.common.validator.ValidatorUtils;
import com.kaori.modules.sys.service.SysRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaori.modules.sys.entity.SysRoleEntity;
import com.kaori.modules.sys.service.SysRoleService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:59
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    /**
     * 列表
     */
    @ApiOperation(value="获取角色列表")
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysRoleService.queryPage(params);

        return R.ok().put("page", page);
    }



    @ApiOperation(value="获取角色信息")
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("id") String roleId){
        SysRoleEntity role = sysRoleService.getById(roleId);

        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return R.ok().put("role", role);
    }

    /**
     * 保存
     */
    @ApiOperation(value="添加角色信息")
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody SysRoleEntity sysRole){
        sysRoleService.saveRole(sysRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value="修改角色信息")
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody SysRoleEntity sysRole){
        ValidatorUtils.validateEntity(sysRole);
        sysRoleService.update(sysRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value="删除角色信息")
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public R delete(@RequestBody String[] ids){
        sysRoleService.deleteBatch(ids);

        return R.ok();
    }

}
