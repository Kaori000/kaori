package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.kaori.common.annotation.SysLog;
import com.kaori.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaori.modules.sys.entity.SysConfigEntity;
import com.kaori.modules.sys.service.SysConfigService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:59
 */
@Api("系统配置")
@RestController
@RequestMapping("sys/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 列表
     */
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("id") String id){
        SysConfigEntity sysConfig = sysConfigService.getById(id);

        return R.ok().put("sysConfig", sysConfig);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody SysConfigEntity sysConfig){
        sysConfigService.save(sysConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody SysConfigEntity sysConfig){
        ValidatorUtils.validateEntity(sysConfig);
        sysConfigService.updateById(sysConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R delete(@RequestBody String[] ids){
        sysConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
