package com.dfjinxin.auth.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.dfjinxin.api.vo.user.UserInfo;
import com.dfjinxin.auth.common.util.jwt.JWTInfo;
import com.dfjinxin.auth.server.feign.IUserService;
import com.dfjinxin.auth.server.service.AuthService;
import com.dfjinxin.auth.server.util.user.JwtAuthenticationRequest;
import com.dfjinxin.auth.server.util.user.JwtTokenUtil;
import com.dfjinxin.common.exception.auth.UserInvalidException;
import com.dfjinxin.common.msg.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        R r = userService.validate(authenticationRequest);
//        UserInfo userInfo = (UserInfo)(r.get("data"));
        UserInfo userInfo = JSON.parseObject(JSON.toJSONString(r.get("data")),UserInfo.class);
        if (null != userInfo && !StringUtils.isEmpty(userInfo.getId())) {
            return jwtTokenUtil.generateToken(new JWTInfo(userInfo.getUsername(), userInfo.getId() + "", userInfo.getName()));
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
