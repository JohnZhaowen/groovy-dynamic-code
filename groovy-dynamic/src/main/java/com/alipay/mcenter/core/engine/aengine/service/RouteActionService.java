/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service;

/**
 * 动作route处理服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface RouteActionService {

    /**
     * 按动作route的唯一识别标志,执行动作route
     * @param routePoint
     * @param sourceInput
     */
    void callRouteXml(String routePoint, Object sourceInput);

    /**
     * 执行route
     * @param routeKey
     * @param sourceInput
     */
    void callRoute(String routeKey, Object sourceInput);

    /**
     * 按source,线性执行所有route
     * @param source
     * @param sourceData
     */
    void callSourceRoute(String source, Object sourceData);

}
