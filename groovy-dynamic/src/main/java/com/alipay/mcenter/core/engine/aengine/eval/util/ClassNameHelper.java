/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 装载脚本的类名util
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ClassNameHelper {

    protected static final AtomicLong classIdSeed = new AtomicLong(0L);

    /**
     * 获得一个唯一的类名
     */
    public static String generator() {
        return Long.toString(classIdSeed.incrementAndGet());
    }
}
