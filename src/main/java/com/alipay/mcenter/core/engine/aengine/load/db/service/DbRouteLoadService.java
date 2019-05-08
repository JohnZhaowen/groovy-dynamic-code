/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.service;

import com.alipay.mcenter.core.engine.aengine.model.Route;
import com.alipay.mcenter.core.engine.aengine.model.Script;

import java.util.List;

/**
 * db版动作流配置加载服务
 * @author chingsung.zihong
 * @version $Id: 2017-06-08 $
 */
public interface DbRouteLoadService {

    /**
     * 按route得key加载整个route配置
     * @param routeKey
     */
    Route loadByRouteKey(String routeKey);

    /**
     * 按source得key加载整个source配置
     * @param source
     */
    List<Route> loadBySource(String source);

    /**
     * 按scriptKey加载script
     * @param scriptKey
     */
    Script loadScript(String scriptKey);
}
