package com.dfjinxin.auth.server.controller;

import com.dfjinxin.auth.server.service.AuthService;
import com.dfjinxin.auth.server.util.user.JwtAuthenticationRequest;
import com.dfjinxin.common.msg.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("jwt")
@Slf4j
public class AuthController {
    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public R createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        log.info(authenticationRequest.getUsername()+" require logging...");
        final String token = authService.login(authenticationRequest);
        return R.ok().put("token", token);
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public R refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return R.ok().put("token", refreshedToken);
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public R verify(String token) throws Exception {
        authService.validate(token);
        return R.ok();
    }

}
