package com.dfjinxin.auth.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(value = "dfjinxin-fast",fallback = AdminServiceFeignFall.class)
public interface AdminServiceFeign {

    @RequestMapping(value = "/api/user/permisson", method = RequestMethod.POST)
    public Set<String> permisson(@RequestParam("userId") String userId);

}
