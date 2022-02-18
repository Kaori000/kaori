package com.kaori.modules.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaori.common.annotation.SysLog;
import com.kaori.common.validator.ValidatorUtils;
import com.kaori.modules.user.service.VeteransAwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaori.modules.user.entity.VeteransEntity;
import com.kaori.modules.user.service.VeteransService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-02-18 11:06:43
 */
@Api(tags = "退役军人模块")
@RestController
@RequestMapping("user/veterans")
public class VeteransController {
    @Autowired
    private VeteransService veteransService;

    @Autowired
    private VeteransAwardService veteransAwardService;

    /**
     * 列表
     */
    @SysLog("列表")
    @ApiOperation(value = "退役军人列表")
    @GetMapping("/list")
    @RequiresPermissions("user:veterans:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = veteransService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @ApiOperation(value = "退役军人信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("user:veterans:info")
    public R info(@PathVariable("id") String id){
        VeteransEntity veterans = veteransService.getById(id);

        return R.ok().put("veterans", veterans);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @ApiOperation(value = "退役军人添加")
    @PostMapping("/save")
    @RequiresPermissions("user:veterans:save")
    public R save(@RequestBody VeteransEntity veterans){
        veteransService.save(veterans);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @ApiOperation(value = "退役军人修改")
    @PatchMapping("/update")
    @RequiresPermissions("user:veterans:update")
    public R update(@RequestBody VeteransEntity veterans){
        ValidatorUtils.validateEntity(veterans);
        veteransService.updateById(veterans);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @ApiOperation(value = "退役军人删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("user:veterans:delete")
    public R delete(@RequestBody String[] ids){
        veteransService.removeByIds(Arrays.asList(ids));
        for (int i = 0; i < ids.length; i++) {
            veteransAwardService.removeAwards(ids[i]);
        }
        return R.ok();
    }

}
