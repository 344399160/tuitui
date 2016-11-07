package com.qbin.crawlers.common.cache.service.impl;

import com.qbin.crawlers.common.cache.service.RedisTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * 描述：Redis初始化服务
 * author qiaobin   2016/8/31 14:19.
 */
@Service
public class RedisTemplateImpl implements RedisTemplate {

    @Autowired
    private Environment environment;

    private Jedis jedis;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private int port;

    /**
      * 功能描述：打开redis连接
      * @author qiaobin
      * @date 2016/8/31  14:34
      */
    private void open() {
        url = environment.getRequiredProperty("redis.host");
        port = Integer.parseInt(environment.getRequiredProperty("redis.port"));
        jedis = new Jedis(url, port);
    }

    /**
      * 功能描述：获取key值
      * @author qiaobin
      * @date 2016/8/31  14:35
      * @param key 键
      */
    public Object get(String key) {
        this.open();
        Object obj = jedis.get(key);
        this.jedis.close();
        return obj;
    }

    /**
      * 功能描述：获取key值
      * @author qiaobin
      * @date 2016/8/31  14:35
      * @param key 键
      */
    public Object get(byte[] key) {
        this.open();
        Object obj = jedis.get(key);
        this.jedis.close();
        return obj;
    }

    /**
     * 功能描述：设置键值
     * @author qiaobin
     * @date 2016/8/31  14:35
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value) {
        this.open();
        jedis.set(key, value);
        this.jedis.close();
    }

    /**
     * 功能描述：获取key值
     * @author qiaobin
     * @date 2016/8/31  14:35
     * @param key 键
     * @param value 值
     */
    public void set(byte[] key, byte[] value) {
        this.open();
        jedis.set(key, value);
        this.jedis.close();
    }

    /**
      * 功能描述：删除键值
      * @author qiaobin
      * @date 2016/8/31  14:37
      * @param key 键
      */
    public void delete(String key) {
        this.open();
        jedis.del(key);
        this.jedis.close();
    }

    /**
      * 功能描述：删除键值
      * @author qiaobin
      * @date 2016/8/31  14:37
      * @param key 键
      */
    public void delete(byte[] key) {
        this.open();
        jedis.del(key);
        this.jedis.close();
    }

    /**
     * 功能描述：设置键值
     * @author qiaobin
     * @date 2016/8/31  14:35
     * @param key 键
     * @param value 值
     * @param second 过期时间（秒）
     */
    public void setExpire(String key, int second, String value) {
        this.open();
        jedis.setex(key, second, value);
        this.jedis.close();
    }

    /**
     * 功能描述：设置键值
     * @author qiaobin
     * @date 2016/8/31  14:35
     * @param key 键
     * @param value 值
     * @param second 过期时间（秒）
     */
    public void setExpire(byte[] key, int second, byte[] value) {
        this.open();
        jedis.setex(key, second, value);
        this.jedis.close();
    }

}
