package com.ilovesshan.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.handler.CustomObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/10
 * @description:
 */

@Slf4j
@Component
public class RedisTemplate {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private CustomObjectMapper objectMapper;

    /**
     * 持久化 字符串
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     * @return
     */
    public String set(String key, String value, Long expire) {
        Jedis jedis = jedisPool.getResource();
        String executorValue = null;
        try {
            if (expire <= 0) {
                executorValue = jedis.set(key, value);
            } else {
                executorValue = jedis.setex(key, expire, value);
            }
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return executorValue;
    }


    /**
     * 获取 字符串
     *
     * @param key 键
     * @return
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String executorValue = null;
        try {
            executorValue = jedis.get(key);
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return executorValue;
    }


    /**
     * 持久化 对象
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     * @return
     */
    public String setObject(String key, Object value, Long expire) {
        Jedis jedis = jedisPool.getResource();
        String executorValue = null;
        try {
            String valueString = objectMapper.writeValueAsString(value);
            if (expire <= 0) {
                executorValue = jedis.set(key, valueString);
            } else {
                executorValue = jedis.setex(key, expire, valueString);
            }
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return executorValue;
    }


    /**
     * 获取 对象
     *
     * @param key 键
     * @return
     */
    public <T> T getObject(String key, TypeReference<T> clazz) {
        Jedis jedis = jedisPool.getResource();
        T executorValue = null;
        try {
            String value = jedis.get(key);
            executorValue = objectMapper.readValue(value, clazz);
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return executorValue;
    }

    /**
     * 对key进行延时
     *
     * @param key    键
     * @param expire 延时事件
     * @return
     */
    public long expire(String key, Long expire) {
        Jedis jedis = jedisPool.getResource();
        long l = 0L;
        try {
            // 设置成功返回 1 。 当 key 不存在或者不能为 key 设置过期时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的过期时间)返回 0 。
            l = jedis.expire(key, expire);
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return l;
    }

    /**
     * 删除 数据
     *
     * @param keys 键列表
     * @return
     */
    public long remove(String... keys) {
        Jedis jedis = jedisPool.getResource();
        long del = 0L;
        try {
            del = jedis.del(keys);
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return del;
    }

    /**
     * 获取 keys
     *
     * @param pattern 表达式
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = null;
        try {
            keys = jedis.keys(pattern);
        } catch (Exception e) {
            log.error("redis  executor error: ", e);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return keys;
    }
}
