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

import com.kaori.modules.sys.entity.SysLogEntity;
import com.kaori.modules.sys.service.SysLogService;
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
@RequestMapping("sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:log:info")
    public R info(@PathVariable("id") String id){
        SysLogEntity sysLog = sysLogService.getById(id);

        return R.ok().put("sysLog", sysLog);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("sys:log:save")
    public R save(@RequestBody SysLogEntity sysLog){
        sysLogService.save(sysLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("sys:log:update")
    public R update(@RequestBody SysLogEntity sysLog){
        ValidatorUtils.validateEntity(sysLog);
        sysLogService.updateById(sysLog);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:log:delete")
    public R delete(@RequestBody String[] ids){
        sysLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}