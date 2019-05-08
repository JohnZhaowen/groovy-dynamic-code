/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.Util;

/**
 * 缓存key管理
 * @author chingsung.zihong
 * @version $Id: 2017-06-09 $
 */
public class CacheKey {

    public static String getSourceRoutesKey(String routeKey) {
        return "source_routes_" + routeKey;
    }

    public static String getRouteActionsKey(String routeKey) {
        return "route_actions_" + routeKey;
    }

    public static String getRouteConnectionsKey(String routeKey) {
        return "route_connections" + routeKey;
    }

    public static String getVersionKey() {
        return "route_action_version";
    }

    public static String getScriptKey(String scriptKey) {
        return "script_" + scriptKey;
    }

    public final static String      cacheGroupKey = "MC_ROUTE_ACTION_GROUP";

}
