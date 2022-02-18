package com.kaori.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.kaori.common.utils.CommonUtils;
import com.kaori.modules.sys.vo.LoginLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Api(tags="系统日志")
@RestController
@RequestMapping("sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;


    /**
     * 列表
     */
    @ApiOperation(value="获取日志列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(LoginLogVo params){
        Map<String ,Object> param = CommonUtils.convertToMap(params);
        PageUtils page = sysLogService.queryPage(param);
        List<SysLogEntity> sysLogEntityList = (List<SysLogEntity>) page.getList();

        return R.ok().put("count", page.getTotalCount()).put("data",sysLogEntityList);
    }

}
