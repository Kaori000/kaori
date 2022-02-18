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

import com.kaori.modules.user.entity.CompanyEntity;
import com.kaori.modules.user.service.CompanyService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-02-18 11:06:43
 */
@Api(tags = "企业")
@RestController
@RequestMapping("user/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 列表
     */
    @ApiOperation(value = "企业列表")
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("user:company:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = companyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查看企业信息")
    @SysLog("信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("user:company:info")
    public R info(@PathVariable("id") String id){
        CompanyEntity company = companyService.getById(id);

        return R.ok().put("company", company);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @ApiOperation(value = "企业添加")
    @PostMapping("/save")
    @RequiresPermissions("user:company:save")
    public R save(@RequestBody CompanyEntity company){
        companyService.save(company);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @ApiOperation(value = "企业修改")
    @PatchMapping("/update")
    @RequiresPermissions("user:company:update")
    public R update(@RequestBody CompanyEntity company){
        ValidatorUtils.validateEntity(company);
        companyService.updateById(company);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "企业删除")
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("user:company:delete")
    public R delete(@RequestBody String[] ids){
        companyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
