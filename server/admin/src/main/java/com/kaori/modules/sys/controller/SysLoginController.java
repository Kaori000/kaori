package com.kaori.modules.sys.controller;

import com.kaori.common.utils.MessageCode;
import com.kaori.common.utils.R;
import com.kaori.common.utils.StringUtil;
import com.kaori.modules.sys.entity.SysUserEntity;
import com.kaori.modules.sys.redis.RedisUtils;
import com.kaori.modules.sys.shiro.ShiroUtils;
import com.kaori.modules.sys.vo.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
public class SysLoginController {
    @Value("${kaori.isSingle}")
    private boolean isSingle;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisOperationsSessionRepository redisOperationsSessionRepository;

    @PostMapping(value = "/sys/login")
    public R login(@RequestBody LoginVo loginObject, HttpServletRequest request) {
        String username = loginObject.getUsername();
        String password = loginObject.getPassword();
        return loginMethod(username, password, request);
    }

    private R loginMethod(String username, String password, HttpServletRequest request) {
        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = null;
        try {
            token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(MessageCode.LOGIN_USER_NOT_EXIT.getCode(), e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error(MessageCode.LOGIN_USER_NAME_OR_PASSWORD_INCORRECT.getCode(), MessageCode.LOGIN_USER_NAME_OR_PASSWORD_INCORRECT.getMsg());
        } catch (LockedAccountException e) {
            return R.error(MessageCode.LOGIN_UNER_ACCOUNT_FROZEN.getCode(), MessageCode.LOGIN_UNER_ACCOUNT_FROZEN.getMsg());
        } catch (AuthenticationException e) {
            return R.error(MessageCode.LOGIN_USER_ACCOUNT_VERIFICATION_ERROR.getCode(), MessageCode.LOGIN_USER_ACCOUNT_VERIFICATION_ERROR.getMsg());
        }

        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();


        if (isSingle) {
            //查看是否之前有登录的删除掉
            String lastSessionId = redisUtils.getSessionByUserId(user.getId());
            if (StringUtil.isNotEmpty(lastSessionId)) {
                redisOperationsSessionRepository.deleteById(lastSessionId);
            }
            //设置session对应关系
            redisUtils.setSessionByUserId(user.getId(), ShiroUtils.getSubject().getSession().getId().toString());
        }
        return R.ok().put("user", user).put("session", Base64.getEncoder()
                .encodeToString(ShiroUtils.getSubject()
                        .getSession()
                        .getId()
                        .toString()
                        .getBytes()
                ));
    }

    @GetMapping(value = "logout")
    public R logout() {
        redisOperationsSessionRepository.deleteById(ShiroUtils.getSession().getId().toString());
        ShiroUtils.logout();
        return R.ok();
    }
}
