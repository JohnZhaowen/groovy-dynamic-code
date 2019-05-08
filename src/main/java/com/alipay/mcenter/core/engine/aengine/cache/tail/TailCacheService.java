package com.alipay.mcenter.core.engine.aengine.cache.tail;

/**
 * db版 tail 缓存服务
 * @author chingsung.zihong
 * @version $Id: 2017-06-08 $
 */
public interface TailCacheService {

    /**
     * 插入缓存(更新覆盖)
     * @param prefix
     * @param key
     * @param value
     */
    void insert(String prefix, String key, Object value);

    /**
     * 查询缓存
     * @param prefix
     * @param key
     */
    Object query(String prefix, String key);

    /**
     * 删除
     * @param prefix
     * @param key
     */
    Boolean remove(String prefix,String key);
}
