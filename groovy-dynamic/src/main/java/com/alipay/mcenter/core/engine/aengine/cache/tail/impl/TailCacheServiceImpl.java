//package com.alipay.mcenter.core.engine.aengine.cache.tail.impl;
//
//
//import com.alipay.mcenter.core.engine.aengine.load.db.Util.HessianSerializationUtils;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alipay.mcenter.core.engine.aengine.cache.tail.TailCacheService;
//import com.alipay.zcache.impl.RefreshableCommonTairCacheManager;
//
///**
// * @see TailCacheService
// */
//public class TailCacheServiceImpl implements TailCacheService {
//
//    private static final Logger               logger    = LoggerFactory
//                                                            .getLogger(TailCacheServiceImpl.class);
//
//    /**
//     * 内存版
//     */
//    private RefreshableCommonTairCacheManager tairCache;
//
//    /**
//     * 默认过期时间18天
//     */
//    private final int                         defExpire = 18 * 24 * 60 * 60;
//
//    /**
//     * @see TailCacheService#insert(String, String, Object)
//     */
//    @Override
//    public void insert(String prefix, String key, Object value) {
//        String _key = key;
//        if (!StringUtil.isEmpty(prefix)) {
//            _key = key + "_" + prefix;
//        }
//        byte[] bytes = null;
//        if (value instanceof byte[]) {
//            bytes = (byte[]) value;
//        } else {
//            bytes = HessianSerializationUtils.hessian2Serialize(value);
//        }
//        tairCache.putObjectWithExpire(_key, bytes, this.defExpire);
//    }
//
//    /**
//     * @see TailCacheService#query(String, String)
//     */
//    @Override
//    public Object query(String prefix, String key) {
//        String _key = key;
//        if (!StringUtils.isEmpty(prefix)) {
//            _key = key + "_" + prefix;
//        }
//        try {
//            Object result = tairCache.getObject(_key);
//            if (result != null) {
//                byte[] bytes = (byte[]) result;
//                return HessianSerializationUtils.hessian2Deserialize(bytes);
//            }
//        } catch (Exception e) {
//            logger.warn("TairCacheDAO query failed ,key: " + _key);
//        }
//        return null;
//    }
//
//    /**
//     * @see TailCacheService#remove(String,String)
//     */
//    @Override
//    public Boolean remove(String prefix,String key) {
//        String _key = key;
//        if (!StringUtil.isEmpty(prefix)) {
//            _key = key + "_" + prefix;
//        }
//        return tairCache.removeObject(_key);
//    }
//
//    public void setTairCache(RefreshableCommonTairCacheManager tairCache) {
//        this.tairCache = tairCache;
//    }
//}
