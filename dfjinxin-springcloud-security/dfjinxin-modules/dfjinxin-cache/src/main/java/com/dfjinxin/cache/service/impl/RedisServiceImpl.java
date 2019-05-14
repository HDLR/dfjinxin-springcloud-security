package com.dfjinxin.cache.service.impl;

import com.dfjinxin.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void delete(Object key){
        redisTemplate.delete(key);
    }

}
