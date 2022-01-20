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

import com.kaori.modules.sys.entity.SysLoginLogEntity;
import com.kaori.modules.sys.service.SysLoginLogService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:58
 */
@RestController
@RequestMapping("sys/loginlog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 列表
     */
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:loginlog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLoginLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:loginlog:info")
    public R info(@PathVariable("id") String id){
        SysLoginLogEntity sysLoginLog = sysLoginLogService.getById(id);

        return R.ok().put("sysLoginLog", sysLoginLog);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("sys:loginlog:save")
    public R save(@RequestBody SysLoginLogEntity sysLoginLog){
        sysLoginLogService.save(sysLoginLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("sys:loginlog:update")
    public R update(@RequestBody SysLoginLogEntity sysLoginLog){
        ValidatorUtils.validateEntity(sysLoginLog);
        sysLoginLogService.updateById(sysLoginLog);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:loginlog:delete")
    public R delete(@RequestBody String[] ids){
        sysLoginLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}