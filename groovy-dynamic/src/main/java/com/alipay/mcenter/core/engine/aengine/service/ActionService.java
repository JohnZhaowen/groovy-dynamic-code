/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service;

import com.alipay.mcenter.core.engine.aengine.model.Action;
import com.alipay.mcenter.core.engine.aengine.model.Result;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import com.alipay.mcenter.core.engine.aengine.service.model.RouteContext;

import java.util.Map;

/**
 * 执行处理动作服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface ActionService {

    /**
     * 单独没有上下文的执行一个动作
     * @param action
     * @param input
     */
    Result callAction(Action action, Object input);

    /**
     * 单独没有上下文的运行一个脚本,无对象缓存
     * @param script
     * @param input
     */
    Result callScript(Script script, Object input);

    /**
     * 单独没有上下文的运行一个脚本,无对象缓存
     * @param scriptKey
     * @param input
     */
    Result callScript(String scriptKey, Object input);

    /**
     * 在route中,有上下文的执行一个动作
     * @param action
     * @param ctx
     */
    Result callAction(Action action, RouteContext ctx);

    /**
     * 情况tail缓存
     * @param key
     */
    Boolean clearCache(String key, Map param);
}
