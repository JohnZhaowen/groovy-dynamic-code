/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.service;

/**
 * db版cache清除
 * @author chingsung.zihong
 * @version $Id: 2017-06-13 $
 */
public interface DbRouteCacheClearService {

    /**
     * 按source得key加载整个source配置
     * @param source
     */
    Boolean clearSource(String source);

}
