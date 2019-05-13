package com.dfjinxin.auth.client.feign;

import com.dfjinxin.common.exception.auth.FeignException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminServiceFeignFall implements AdminServiceFeign {

    @Override
    public Set<String> permisson(String userId){
        throw new FeignException("访问后台管理系统dfjinxin-admin异常");
    }
}
