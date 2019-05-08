/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.impl;

import com.alipay.mcenter.core.engine.aengine.exception.ActionRetryException;
import com.alipay.mcenter.core.engine.aengine.exception.ScriptCompileException;
import com.alipay.mcenter.core.engine.aengine.exception.ScriptObjectRunException;
import com.alipay.mcenter.core.engine.aengine.load.xml.service.XmlRouteLoadService;
import com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService;
import com.alipay.mcenter.core.engine.aengine.model.Action;
import com.alipay.mcenter.core.engine.aengine.model.Result;
import com.alipay.mcenter.core.engine.aengine.model.Route;
import com.alipay.mcenter.core.engine.aengine.service.ActionService;
import com.alipay.mcenter.core.engine.aengine.service.RouteActionService;
import com.alipay.mcenter.core.engine.aengine.service.f.RouteAction;
import com.alipay.mcenter.core.engine.aengine.service.model.RouteContext;
import com.alipay.mcenter.core.engine.aengine.service.util.Alog;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @see com.alipay.mcenter.core.engine.aengine.service.RouteActionService
 */
public class RouteActionServiceImpl extends RouteAction implements RouteActionService {

    private BeanFactoryService  beanFactoryService;

    private ActionService       actionService;

    private XmlRouteLoadService xmlRouteLoadService;

    private DbRouteLoadService  dbRouteLoadService;

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.RouteActionService#callRouteXml(String, Object)
     */
    @Override
    public void callRouteXml(String routePoint, Object sourceInput) {
        Route route = xmlRouteLoadService.loadBySource(routePoint);

        doRoute(route, sourceInput);
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.RouteActionService#callRoute(String, Object)
     */
    @Override
    public void callRoute(String routeKey, Object sourceInput) {
        Route route = dbRouteLoadService.loadByRouteKey(routeKey);

        doRoute(route, sourceInput);
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.RouteActionService#callSourceRoute(String, Object)
     */
    @Override
    public void callSourceRoute(String source, Object sourceData) {
        List<Route> routes = dbRouteLoadService.loadBySource(source);
        if (routes != null && routes.size() > 0) {
            for (Route route : routes) {
                try {
                    doRoute(route, sourceData);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    /**
     * 处理一个route
     */
    private void doRoute(Route route, Object sourceInput) {
        if (route.getActions().isEmpty()) {
            return;
        }

        if (!routeCron(route)) {
            return;
        }

        RouteContext ctx = initContext(route, beanFactoryService, route.getKey(), sourceInput);

        Action action = route.getActions().get(route.getBeginAc());

        doAction(action, ctx, route);
    }

    /**
     * 递归用,执行一个action
     */
    private void doAction(Action action, RouteContext ctx, Route route) {
        Result result = new Result();
        ctx.setAction(action.getKey());
        Alog.initMDC(ctx);
        try {
            result = actionService.callAction(action, ctx);
            if (!result.isJudge()) {
                return;
            }
            if (!result.isSuccess() && action.getMust()) {
                return;
            }
        } catch (ActionRetryException e) {
            Alog.warn(ctx, "actionRetryException");
            return;
        } catch (ScriptCompileException e) {
            Alog.error(ctx, e, "scriptCompileException");
            return;
        } catch (ScriptObjectRunException e) {
            Alog.error(ctx, e, "scriptObjectRunException");
            if (!e.getResult().isJudge()) {
                return;
            }
            if (!e.getResult().isSuccess() && action.getMust()) {
                return;
            }
        } catch (Exception e) {
            Alog.error(ctx, e, "routeactionUnknowException");
            return;
        } finally {

            Alog.info(ctx, "judge:{0},result:{1}", result.isJudge(), result.isSuccess());
            //Alog.clearMDC();
        }
        ctx.putActionExcuteInfo(action.getKey(), result);
        String nextActionKey = route.getNextKey(action.getKey());
        if (StringUtils.isNotBlank(nextActionKey)) {
            ctx.putInvars(nextActionKey, result.getOutData());

            doAction(route.getActions().get(nextActionKey), ctx, route);
        }
        return;
    }

    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    public void setXmlRouteLoadService(XmlRouteLoadService xmlRouteLoadService) {
        this.xmlRouteLoadService = xmlRouteLoadService;
    }

    public void setBeanFactoryService(BeanFactoryService beanFactoryService) {
        this.beanFactoryService = beanFactoryService;
    }

    public void setDbRouteLoadService(DbRouteLoadService dbRouteLoadService) {
        this.dbRouteLoadService = dbRouteLoadService;
    }
}
