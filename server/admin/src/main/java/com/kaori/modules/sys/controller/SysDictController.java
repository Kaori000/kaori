package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kaori.common.annotation.SysLog;
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

import com.kaori.modules.sys.entity.SysDictEntity;
import com.kaori.modules.sys.service.SysDictService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:59
 */
@Api(tags="数据字典")
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @ApiOperation(value = "数据字典列表")
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @ApiOperation(value = "获取单个数据字典项")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public R info(@PathVariable("id") String id){
        SysDictEntity sysDict = sysDictService.getById(id);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 信息
     */
    @SysLog("信息")
    @ApiOperation(value = "根据数据字典类型获取数据")
    @GetMapping("/type/{type}")
    @RequiresPermissions("sys:dict:info")
    public R type(@PathVariable("type") String type){
        List<SysDictEntity> sysDict = sysDictService.getByType(type);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @PostMapping("/save")
    @ApiOperation(value = "添加数据字典")
    @RequiresPermissions("sys:dict:save")
    public R save(@RequestBody SysDictEntity sysDict){
        sysDictService.save(sysDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @ApiOperation(value = "修改字典列表")
    @PatchMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public R update(@RequestBody SysDictEntity sysDict){
        ValidatorUtils.validateEntity(sysDict);
        sysDictService.updateById(sysDict);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @ApiOperation(value = "删除字典列表")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public R delete(@RequestBody String[] ids){
        sysDictService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
