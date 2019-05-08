/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.temp.groovyservice;

import com.alipay.mcenter.core.engine.aengine.service.model.RouteContext;

/**
 * 提供给动态脚本实现的基础接口
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface CommonScript {

    /**
     * 供groovy/java动态代码实现用,不得变更
     * @param ctx
     * @return
     * @throws Exception
     */
    Object eval(RouteContext ctx, Object input) throws Exception;

    /**
     * 供groovy/java动态代码实现用,不得变更,eval前判断
     * @param ctx
     * @return
     * @throws Exception
     */
    Boolean judge(RouteContext ctx, Object input) throws Exception;
}
