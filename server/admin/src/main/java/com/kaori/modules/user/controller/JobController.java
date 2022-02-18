package com.kaori.modules.user.controller;

import java.util.Arrays;
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

import com.kaori.modules.user.entity.JobEntity;
import com.kaori.modules.user.service.JobService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-02-18 11:06:43
 */
@Api(tags = "企业职位")
@RestController
@RequestMapping("user/job")
public class JobController {
    @Autowired
    private JobService jobService;

    /**
     * 列表
     */
    @ApiOperation(value = "职位列表")
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("user:job:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jobService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查看职位")
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("user:job:info")
    public R info(@PathVariable("id") String id){
        JobEntity job = jobService.getById(id);

        return R.ok().put("job", job);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "职位添加")
    @SysLog("保存")
    @PostMapping("/save")
    @RequiresPermissions("user:job:save")
    public R save(@RequestBody JobEntity job){
        jobService.save(job);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "职位修改")
    @SysLog("修改")
    @PatchMapping("/update")
    @RequiresPermissions("user:job:update")
    public R update(@RequestBody JobEntity job){
        ValidatorUtils.validateEntity(job);
        jobService.updateById(job);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "职位删除")
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("user:job:delete")
    public R delete(@RequestBody String[] ids){
        jobService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
