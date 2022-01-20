package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.kaori.common.annotation.SysLog;
import com.kaori.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaori.modules.sys.entity.SysRoleDeptEntity;
import com.kaori.modules.sys.service.SysRoleDeptService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:59
 */
@RestController
@RequestMapping("sys/roledept")
public class SysRoleDeptController {
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 列表
     */
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:roledept:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysRoleDeptService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:roledept:info")
    public R info(@PathVariable("roleId") String roleId){
        SysRoleDeptEntity sysRoleDept = sysRoleDeptService.getById(roleId);

        return R.ok().put("sysRoleDept", sysRoleDept);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("sys:roledept:save")
    public R save(@RequestBody SysRoleDeptEntity sysRoleDept){
        sysRoleDeptService.save(sysRoleDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("sys:roledept:update")
    public R update(@RequestBody SysRoleDeptEntity sysRoleDept){
        ValidatorUtils.validateEntity(sysRoleDept);
        sysRoleDeptService.updateById(sysRoleDept);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:roledept:delete")
    public R delete(@RequestBody String[] roleIds){
        sysRoleDeptService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}