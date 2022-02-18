package com.kaori.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaori.common.annotation.SysLog;
import com.kaori.common.utils.CommonConst;
import com.kaori.common.utils.MessageCode;
import com.kaori.common.validator.Assert;
import com.kaori.common.validator.ValidatorUtils;
import com.kaori.modules.sys.entity.SysRoleEntity;
import com.kaori.modules.sys.entity.SysUserRoleEntity;
import com.kaori.modules.sys.service.SysRoleService;
import com.kaori.modules.sys.service.SysUserRoleService;
import com.kaori.modules.sys.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaori.modules.sys.entity.SysUserEntity;
import com.kaori.modules.sys.service.SysUserService;
import com.kaori.common.utils.PageUtils;
import com.kaori.common.utils.R;



/**
 * 
 *
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-01-18 14:59:58
 */
@Api(tags="用户管理")
@RestController
@RequestMapping("sys/user")
public class SysUserController extends AbstractController{
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleService sysRoleService;

    /**
     * 列表
     */
    @ApiOperation(value="所有用户列表")
    @SysLog("列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("信息")
    @ApiOperation(value="查看单个用户")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("id") String id){
        SysUserEntity sysUser = sysUserService.getById(id);
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(sysUser.getId());
        sysUser.setRoleIds(roleIdList);
        //获取角色名
        List<String> roleNameList = new ArrayList<String>();
        for(String roleId:roleIdList){
            SysRoleEntity role = sysRoleService.getById(roleId);
            roleNameList.add(role.getRoleName());
        }
        sysUser.setRoleNames(roleNameList);
        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 信息
     */
    @SysLog("信息")
    @ApiOperation(value="获取当前用户")
    @GetMapping("/curr")
    public R curr(){
        SysUserEntity sysUser = sysUserService.getById(ShiroUtils.getUserId());
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(sysUser.getId());
        sysUser.setRoleIds(roleIdList);
        //获取角色名
        List<String> roleNameList = new ArrayList<String>();
        for(String roleId:roleIdList){
            SysRoleEntity role = sysRoleService.getById(roleId);
            roleNameList.add(role.getRoleName());
        }
        sysUser.setRoleNames(roleNameList);
        return R.ok().put("sysUser", sysUser).put("permsList", ShiroUtils.getSession().getAttribute(CommonConst.PERMS_LIST));
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @ApiOperation(value="保存用户信息")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity sysUser){
        sysUserService.save(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @ApiOperation(value="修改用户信息")
    @PatchMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity sysUser){
        ValidatorUtils.validateEntity(sysUser);
        sysUserService.updateById(sysUser);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @ApiOperation(value="删除用户信息")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody String[] ids){
        if (ArrayUtils.contains(ids, 1L)) {
            return R.error(MessageCode.USER_OPERATION_SYSTEM_ADMIN_CANNOT_DELETE.getCode(), "系统管理员不能删除");
        }

        if (ArrayUtils.contains(ids, getUserId())) {
            return R.error(MessageCode.USER_OPERATION_CURRENT_USER_CANNOT_DELETE.getCode(), "当前用户不能删除");
        }
        sysUserService.removeByIds(Arrays.asList(ids));
        for(String userId:ids){
            QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId);
            sysUserRoleService.remove(queryWrapper);
        }
        return R.ok();
    }

    @ApiOperation(value="修改登录用户密码")
    @SysLog("修改密码")
    @PostMapping("/changePwd")
    public R password(String password,String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error(MessageCode.LOGIN_OLD_PASSWORD_ERROR.getCode(), "原密码不正确");
        }

        return R.ok();
    }

    @ApiOperation(value="根据用户获取角色列表")
    @SysLog("获取角色列表")
    @GetMapping("/roles/{userId}")
    @RequiresPermissions("sys:user:roles")
    public R roleList(@PathVariable Integer userId){
        //查询所有的角色
        List<SysRoleEntity> roleList = sysRoleService.list();
        //创建装角色的list
        List<SysRoleEntity> roleIdList = new ArrayList();
        //根据用户查询所有的角色id
        List<SysUserRoleEntity> userRoleList = sysUserRoleService.list();
        //创建用来装角色id的list
        List<String> roleIds = new ArrayList();
        for(SysUserRoleEntity nav:userRoleList){
            if(userId != null && nav.getUserId().equals(userId)){
                roleIds.add(nav.getRoleId());
            }
        }
        for(SysRoleEntity nav:roleList){
            for(String roleId:roleIds){
                if (nav.getId().equals(roleId)){
                    roleIdList.add(nav);
                }
            }
        }
        return R.ok().put("data",roleIdList);
    }

    @GetMapping("/checkUsername")
    public R checkUser(@RequestParam("username") String username){
        if (!sysUserService.isExist(username)){
            return R.error(512,"用户名已存在");
        }

        return R.ok();
    }

}
