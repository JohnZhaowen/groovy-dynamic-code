/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.util;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.alipay.mcenter.core.engine.aengine.service.model.RouteContext;

/**
 * route-action日志
 * @author chingsung.zihong
 * @version $Id: 2017-05-05 $
 */
public class Alog {

    public static Logger       logger   = LoggerFactory.getLogger("MCENTER-ROUTE-ACTION");

    public static final String infos    = "info";

    public static final String warns    = "warn";

    public static final String errors   = "error";

    public static final String msgType_ = "msgType";

    public static final String ctxId_   = "ctxId";

    public static final String source_  = "source";

    public static final String route_   = "route";

    public static final String action_  = "action";

    /**
     * logger.info
     */
    public static void info(RouteContext rtx, String log, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info("");
        }
    }

    /**
     * logger.warn
     */
    public static void warn(RouteContext rtx, String log, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn("");
        }
    }

    /**
     * logger.error
     */
    public static void error(RouteContext rtx, Throwable e, String log, Object... params) {
        logger.error(
"");
    }

    /**
     * 设置日志上下文
     */
    public static void initMDC(RouteContext ctx) {
        if (StringUtils.isNotBlank(ctx.getCtxId())) {
            MDC.put(ctxId_, ctx.getCtxId());
        }

        MDC.put(msgType_, "C_");

        if (StringUtils.isNotBlank(ctx.getSource())) {
            MDC.put(source_, ctx.getSource());
        }

        if (StringUtils.isNotBlank(ctx.getRoute())) {
            MDC.put(route_, ctx.getRoute());
        }

        if (StringUtils.isNotBlank(ctx.getAction())) {
            MDC.put(action_, ctx.getAction());
        }

    }

    public static void initMDCaction(String action) {
        if (StringUtils.isNotBlank(action) && MDC.get(action_) != null) {
            MDC.put(action_, action);
        }
    }

    /**
     * 清理MDC
     */
//    public static void clearMDC() {
//        if (MDC.getContext() != null) {
//            MDC.getContext().clear();
//        }
//    }
}
