package com.platform.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 *
 * @author shitou
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置过期时间，单位秒
     *
     * @param key     键的名称
     * @param timeout 过期时间
     * @return 成功：true，失败：false
     */
    public boolean setExpireTime(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 通过键删除一个值
     *
     * @param key 键的名称
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键的名称
     * @return 存在：true，不存在：false
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 数据存储
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {

        redisTemplate.boundValueOps(key).set(value);
    }

    /**
     * 数据存储的同时设置过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间
     */
    public void set(String key, Object value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 数据取值
     *
     * @param key 键
     * @return 查询成功：值，查询失败，null
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

}
