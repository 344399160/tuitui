package com.qbin.crawlers.common.cache.service;

/**
 * 描述：Redis初始化服务Service
 * author qiaobin   2016/8/31 14:18.
 */
public interface RedisTemplate {

    /**
     * 功能描述：获取key值
     *
     * @param key 键
     * @author qiaobin
     * @date 2016/8/31  14:35
     */
    public Object get(String key);

    /**
     * 功能描述：获取key值
     *
     * @param key 键
     * @author qiaobin
     * @date 2016/8/31  14:35
     */
    public Object get(byte[] key);

    /**
     * 功能描述：设置键值
     *
     * @param key   键
     * @param value 值
     * @author qiaobin
     * @date 2016/8/31  14:35
     */
    public void set(String key, String value);

    /**
     * 功能描述：获取key值
     *
     * @param key   键
     * @param value 值
     * @author qiaobin
     * @date 2016/8/31  14:35
     */
    public void set(byte[] key, byte[] value);

    /**
     * 功能描述：删除键值
     *
     * @param key 键
     * @author qiaobin
     * @date 2016/8/31  14:37
     */
    public void delete(String key);

    /**
     * 功能描述：删除键值
     *
     * @param key 键
     * @author qiaobin
     * @date 2016/8/31  14:37
     */
    public void delete(byte[] key);

    /**
     * 功能描述：设置键值
     *
     * @param key    键
     * @param value  值
     * @param second 过期时间（秒）
     * @author qiaobin
     * @date 2016/8/31  14:35
     */
    public void setExpire(String key, int second, String value);

    /**
     * 功能描述：设置键值
     *
     * @param key    键
     * @param value  值
     * @param second 过期时间（秒）
     * @author qiaobin
     * @date 2016/8/31  14:35
     */
    public void setExpire(byte[] key, int second, byte[] value);

}
