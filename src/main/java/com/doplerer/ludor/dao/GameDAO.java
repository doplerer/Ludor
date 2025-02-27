package com.doplerer.ludor.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAO {

    private final RedisTemplate<String, Object> redisTemplate;

    public GameDAO(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
