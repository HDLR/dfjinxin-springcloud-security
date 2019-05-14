package io.dfjinxin.admin.modules.auth;

import com.dfjinxin.api.vo.user.UserInfo;
import com.dfjinxin.auth.client.annotation.IgnoreUserToken;
import com.dfjinxin.cache.service.RedisService;
import com.dfjinxin.common.constant.RedisConstants;
import io.dfjinxin.admin.common.annotation.IgnoreResponseAdvice;
import io.dfjinxin.admin.common.utils.EncoderUtils;
import io.dfjinxin.admin.modules.sys.controller.AbstractController;
import io.dfjinxin.admin.modules.sys.entity.SysUserEntity;
import io.dfjinxin.admin.modules.sys.service.PermissonService;
import io.dfjinxin.admin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
public class AuthController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PermissonService permissonService;
    @Autowired
    private RedisService redisService;

    /**
     * 登录
     */
    @IgnoreUserToken
    @IgnoreResponseAdvice
    @PostMapping("/api/user/validate")
    public UserInfo login(@RequestBody Map<String,String> body)throws IOException {

        UserInfo userInfo = new UserInfo();

//		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//		if(!captcha){
//			return R.error("验证码不正确");
//		}

        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(body.get("username"));

        //账号不存在、密码错误
        if(user == null || !(EncoderUtils.getInstance().matches(body.get("password"), user.getPassword()))) {
           return userInfo;
        }

        //账号锁定
        if(user.getStatus() == 0){
            return userInfo;
        }

        //验证成功后清除原先redis的权限缓存
        redisService.delete(RedisConstants.AUTH_PERMISSION + "::" + user.getUserId());

        userInfo.setId(String.valueOf(user.getUserId()));
        userInfo.setUsername(user.getUsername());
        userInfo.setName(user.getUsername());
        userInfo.setPassword(user.getPassword());
//		userInfo.setDescription();
        return userInfo;
    }

    /**
     * 登录
     */
    @IgnoreUserToken
    @IgnoreResponseAdvice
    @PostMapping("/api/user/permisson")
    public Set<String> permisson(@RequestParam("userId") String userId)throws IOException {
        return permissonService.getUserPermissions(Long.valueOf(userId));
    }
}
