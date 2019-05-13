package com.dfjinxin.auth.server.feign;

import com.dfjinxin.api.vo.user.UserInfo;
import com.dfjinxin.auth.server.util.user.JwtAuthenticationRequest;
import com.dfjinxin.common.exception.auth.FeignException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Component
public class UserServiceFall implements IUserService{

    @Override
    public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest){
        throw new FeignException("访问后台管理系统dfjinxin-admin异常");
    }
}
