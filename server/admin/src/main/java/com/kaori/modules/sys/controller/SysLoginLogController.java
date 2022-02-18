package com.kaori.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kaori.common.annotation.SysLog;
import com.kaori.common.utils.CommonUtils;
import com.kaori.common.validator.ValidatorUtils;
import com.kaori.modules.sys.vo.LoginLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags="登录日志模块")
@RestController
@RequestMapping("sys/loginlog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 列表
     */
    @ApiOperation(value="获取登录日志列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:loginlog:list")
    public R list(LoginLogVo params) {
        Map<String ,Object> param = CommonUtils.convertToMap(params);
        PageUtils page = sysLoginLogService.queryPage(param);
        List<SysLoginLogEntity> sysLoginLogEntityList = (List<SysLoginLogEntity>) page.getList();

        return R.ok().put("count", page.getTotalCount()).put("data",sysLoginLogEntityList);
    }

}