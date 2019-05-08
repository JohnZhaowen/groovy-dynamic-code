/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.xml.service;

import com.alipay.mcenter.core.engine.aengine.model.Route;

/**
 * xml版动作流配置加载服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface XmlRouteLoadService {

    /**
     * 按route得key加载整个route配置
     * @param source
     */
    Route loadBySource(String source);
}
