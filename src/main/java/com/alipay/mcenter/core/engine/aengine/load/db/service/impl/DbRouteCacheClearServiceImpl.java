/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.service.impl;

import com.alipay.mcenter.core.engine.aengine.load.db.Util.CacheKey;
import com.alipay.mcenter.core.engine.aengine.load.db.model.RouteActions;
import com.alipay.mcenter.core.engine.aengine.load.db.model.SourceRoute;
import com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteCacheClearService;
import com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService;
import com.alipay.mcenter.core.engine.aengine.cache.tail.TailCacheService;
import com.alipay.mcenter.core.engine.aengine.service.impl.ScriptObjectCacheService;

import java.util.List;

/**
 * @see DbRouteLoadService
 */
public class DbRouteCacheClearServiceImpl implements DbRouteCacheClearService {

    private TailCacheService tailCacheService;

    @Override
    public Boolean clearSource(String sourceKey) {
        Object objv = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getVersionKey());
        if(objv!=null){
            tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getVersionKey());
            return true;
        }

        Object objs = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getScriptKey(sourceKey));
        if(objs!=null){
            tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getScriptKey(sourceKey));
            return true;
        }

        Object obj = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getSourceRoutesKey(sourceKey));
        if(obj!=null){
            tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getSourceRoutesKey(sourceKey));
        }
        if (obj instanceof List) {
            List<SourceRoute> sourceRoutes = (List<SourceRoute>) obj;
            if (sourceRoutes != null) {
                for (SourceRoute sourceRoute : sourceRoutes) {
                    Object objRouteActions = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getRouteActionsKey(sourceRoute.getRouteKey()));
                    if (obj != null && sourceRoute.getRouteKey()!=null) {
                        tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getRouteActionsKey(sourceRoute.getRouteKey()));

                        Object objc = tailCacheService.query(CacheKey.cacheGroupKey,
                                CacheKey.getRouteConnectionsKey(sourceRoute.getRouteKey()));
                        if(objc!=null){
                            tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getRouteConnectionsKey(sourceRoute.getRouteKey()));
                        }
                    }

                    if(objRouteActions instanceof List){
                        List<RouteActions> routeActionsList = (List<RouteActions>)objRouteActions;
                        for (RouteActions routeActions : routeActionsList) {
                            if(routeActions.getAscript()!=null ){
                                tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getScriptKey(routeActions.getAscript()));
                            }

                            if(routeActions.getJudge()!=null){
                                tailCacheService.remove(CacheKey.cacheGroupKey,CacheKey.getScriptKey(routeActions.getJudge()));
                            }

                            ScriptObjectCacheService.getInstance().remove(routeActions.getActionKey());
                        }
                    }
                }
            }
        }
        return true;
    }

    public void setTailCacheService(TailCacheService tailCacheService) {
        this.tailCacheService = tailCacheService;
    }

}
