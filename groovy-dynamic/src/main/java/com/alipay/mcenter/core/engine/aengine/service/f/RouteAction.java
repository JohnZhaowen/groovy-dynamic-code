/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.f;

import org.apache.commons.lang.StringUtils;

import com.alipay.mcenter.core.engine.aengine.eval.expression.CronService;
import com.alipay.mcenter.core.engine.aengine.model.Route;
import com.alipay.mcenter.core.engine.aengine.service.impl.BeanFactoryService;
import com.alipay.mcenter.core.engine.aengine.service.model.RouteContext;
import com.alipay.mcenter.core.engine.aengine.service.model.f.BaseRaCtx;

/**
 * 动作route处理服务基类
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class RouteAction {

    /**
          * 按照封装的cron表达式判断routeaction是否在可执行时间内
     * @param route
     */
    protected boolean routeCron(Route route) {
        if (route == null) {
            return false;
        }
        if (!StringUtils.equals("false", route.getCron())
            && !StringUtils.equals("true", route.getCron())) {
            if (!CronService.getInstance().isNow4agCron(route.getCron())) {
                return false;
            }
        } else if (StringUtils.equals("false", route.getCron())
                   || StringUtils.equals("true", route.getCron())) {
            return Boolean.valueOf(route.getCron());
        }
        return true;
    }

    /**
     * 获得执行routeaction的执行上下文对象
     * @param route
     */
    protected RouteContext initContext(Route route, BeanFactoryService beanFactoryService,
                                       String routePoint, Object sourceInput) {
        RouteContext ctx = new RouteContext();
        ctx.setRoute(route.getKey());
        ctx.setSource(routePoint);
        ctx.putParam(BaseRaCtx.beanservicekey, beanFactoryService);
        ctx.putInvars(route.getBeginAc(), sourceInput);
        return ctx;
    }

    protected RouteContext initContext(BeanFactoryService beanFactoryService) {
        RouteContext ctx = new RouteContext();
        ctx.putParam(BaseRaCtx.beanservicekey, beanFactoryService);
        return ctx;
    }

}
