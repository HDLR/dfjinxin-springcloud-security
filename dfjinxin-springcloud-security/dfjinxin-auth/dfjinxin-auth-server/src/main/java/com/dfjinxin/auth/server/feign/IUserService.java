package com.dfjinxin.auth.server.feign;

import com.dfjinxin.api.vo.user.UserInfo;
import com.dfjinxin.auth.server.util.user.JwtAuthenticationRequest;
import com.dfjinxin.auth.server.configuration.FeignConfiguration;
import com.dfjinxin.common.msg.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Set;

@FeignClient(value = "${admin.application.name}",fallback = UserServiceFall.class)
public interface IUserService {

  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
  public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);

}
