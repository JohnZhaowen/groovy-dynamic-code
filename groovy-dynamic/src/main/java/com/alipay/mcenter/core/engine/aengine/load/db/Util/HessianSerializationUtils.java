///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2017 All Rights Reserved.
// */
//package com.alipay.mcenter.core.engine.aengine.load.db.Util;
//
//import com.alibaba.common.logging.Logger;
//import com.alibaba.common.logging.LoggerFactory;
//import com.taobao.remoting.TRConstants;
//import com.taobao.remoting.serialize.CustomSerialization;
//import com.taobao.remoting.serialize.SerializationUtil;
//
//import java.io.IOException;
//
///**
// * Hession2序列化工具
// * @author chingsung.zihong
// * @version $Id: 2017-06-08 $
// */
//public class HessianSerializationUtils {
//
//    private static final Logger logger = LoggerFactory.getLogger(HessianSerializationUtils.class);
//
//    /**
//     * hessian2的序列化接口，支持阿里枚举，通过对应类的classloader获取序列化器，执行序列化
//     * @param obj 序列化的对象
//     * @return 序列化字节数组
//     */
//    public static byte[] hessian2Serialize(Object obj) {
//        if (obj == null) {
//            return null;
//        }
//        CustomSerialization<Object> tes;
//        ClassLoader classLoader = obj.getClass().getClassLoader();
//        if (classLoader != null) {
//            tes = SerializationUtil.getSerializationByClassLoader(classLoader);
//        } else {
//            tes = SerializationUtil.getDefaultSerialization();
//        }
//
//        try {
//            return tes.serialize(TRConstants.HESSIAN2_SERIALIZE, obj);
//        } catch (Exception e) {
//            logger.warn("hessian2序列化错误", e);
//            return null;
//        }
//    }
//
//    /**
//     * hessian2的反序列化接口，支持阿里枚举，通过对应类的classloader获取序列化器，执行反序列化
//     * @param bytes 序列化对象
//     * @return 实际的对象
//     */
//    public static Object hessian2Deserialize(byte[] bytes) {
//        if (bytes == null) {
//            return null;
//        }
//        CustomSerialization<Object> tes = SerializationUtil.getSerializationByClassLoader(Thread
//            .currentThread().getContextClassLoader());
//
//        try {
//            return tes.deserialize(TRConstants.HESSIAN2_SERIALIZE, bytes, 0);
//        } catch (IOException e) {
//            logger.warn("hessian2反序列化错误", e);
//            return null;
//        }
//    }
//}
