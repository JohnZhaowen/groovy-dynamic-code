///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2017 All Rights Reserved.
// */
//package com.alipay.mcenter.core.engine.aengine.load.db.service.impl;
//
//import com.alipay.mcenter.common.dal.common.MapDao;
//import com.alipay.mcenter.core.engine.aengine.acollection.model.Connection;
//import com.alipay.mcenter.core.engine.aengine.enums.ActionTypeEnum;
//import com.alipay.mcenter.core.engine.aengine.enums.RouteTypeEnum;
//import com.alipay.mcenter.core.engine.aengine.enums.ScriptTypeEnum;
//import com.alipay.mcenter.core.engine.aengine.load.db.Util.CacheKey;
//import com.alipay.mcenter.core.engine.aengine.load.db.model.Ascript;
//import com.alipay.mcenter.core.engine.aengine.load.db.model.RouteActions;
//import com.alipay.mcenter.core.engine.aengine.load.db.model.SourceRoute;
//import com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService;
//import com.alipay.mcenter.core.engine.aengine.cache.tail.TailCacheService;
//import com.alipay.mcenter.core.engine.aengine.model.Action;
//import com.alipay.mcenter.core.engine.aengine.model.Route;
//import com.alipay.mcenter.core.engine.aengine.model.Script;
//
//import java.util.*;
//
///**
// * @see com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService
// */
//public class DbRouteLoadServiceImpl implements DbRouteLoadService {
//
//    private MapDao           mapDao;
//
//    private TailCacheService tailCacheService;
//
//    //默认版本,研发平台将实现版本管理
//
//    /**
//     * @see com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService#loadByRouteKey(String)
//     */
//    @Override
//    public Route loadByRouteKey(String routeKey) {
//        Route route = new Route();
//
//        List<RouteActions> routeActionsList = dbCacheRouteActions(routeKey);
//
//        if (routeActionsList.size() > 0) {
//            RouteActions routeActions = routeActionsList.get(0);
//            route.setKey(routeActions.getRouteKey());
//            route.setCron(routeActions.getAcron());
//            route.setBeginAc(routeActions.getBegin());
//            route.setRouteType(RouteTypeEnum.getByCode(routeActions.getRouteType()));
//        } else {
//            return route;
//        }
//
//        if (routeActionsList.size() > 0) {
//            List<Connection> Connections = dbCacheConnections(routeKey);
//            route.setConnects(Connections);
//        }
//
//        for (RouteActions routeActions : routeActionsList) {
//            Action action = new Action();
//            action.setKey(routeActions.getActionKey());
//            action.setType(ActionTypeEnum.getByCode(routeActions.getActionType()));
//            action.setMust(routeActions.getIsMust() == 1);
//            route.getActions().put(action.getKey(), action);
//
//            action.setScript(dbCacheScript(routeActions.getAscript()));
//
//            action.setJudge(dbCacheScript(routeActions.getJudge()));
//
//            route.getActions().put(action.getKey(), action);
//        }
//
//        return route;
//    }
//
//    /**
//     * @see com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService#loadBySource(String)
//     */
//    @Override
//    public List<Route> loadBySource(String source) {
//        List<SourceRoute> sourceRoutes = dbCacheSourceRoute(source);
//        List<Route> routes = new ArrayList<Route>();
//        if (sourceRoutes != null) {
//            for (SourceRoute sourceRoute : sourceRoutes) {
//                Route route = loadByRouteKey(sourceRoute.getRouteKey());
//                routes.add(route);
//            }
//        }
//        return routes;
//    }
//
//    /**
//     * @see com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService#loadScript(String)
//     */
//    @Override
//    public Script loadScript(String scriptKey) {
//        return dbCacheScript(scriptKey);
//    }
//
//    /**
//     * 读取source下route,db and cache
//     */
//    private List<SourceRoute> dbCacheSourceRoute(String sourceKey) {
//        List<SourceRoute> sourceRoutes;
//        Object obj = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getSourceRoutesKey(sourceKey));
//        if (obj != null && obj instanceof List) {
//            sourceRoutes = (List<SourceRoute>) obj;
//            return sourceRoutes;
//        }
//        sourceRoutes = dbSourceRoute(sourceKey);
//        tailCacheService
//                .insert(CacheKey.cacheGroupKey, CacheKey.getSourceRoutesKey(sourceKey), sourceRoutes);
//        return sourceRoutes;
//    }
//
//    /**
//     * 读取source下route,db
//     */
//    private List<SourceRoute> dbSourceRoute(String sourceKey) {
//        SourceRoute param = new SourceRoute();
//        param.setSourceKey(sourceKey);
//        List<SourceRoute> sourceRoutes = (List<SourceRoute>) mapDao.listObjectQuery(
//                "mdata.routeActionQuery.getsourceroute", param);
//        return sourceRoutes;
//    }
//
//    /**
//     * 读取完整的脚本,db and cache
//     */
//    private Script dbCacheScript(String scriptKey) {
//        Script script;
//        Object obj = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getScriptKey(scriptKey));
//        if (obj != null && obj instanceof Script) {
//            script = (Script) obj;
//            return script;
//        }
//        script = new Script();
//        Ascript ascript = dbScript(scriptKey);
//        if (ascript == null) {
//            return script;
//        }
//        Set<String> aScriptImpts = dbScriptPkg(scriptKey);
//
//        script.setKey(ascript.getScriptKey());
//        script.setScriptType(ScriptTypeEnum.getByCode(ascript.getScriptType()));
//        script.setScriptText(ascript.getScript());
//        script.setImports(aScriptImpts);
//        tailCacheService.insert(CacheKey.cacheGroupKey, CacheKey.getScriptKey(scriptKey), script);
//        return script;
//    }
//
//    /**
//     * 读取route下所有action,不含脚本,脚本单独缓存管理,db and cache
//     */
//    private List<RouteActions> dbCacheRouteActions(String routeKey) {
//        Object obj = tailCacheService.query(CacheKey.cacheGroupKey, CacheKey.getRouteActionsKey(routeKey));
//        if (obj != null && obj instanceof List) {
//            return (List<RouteActions>) obj;
//        }
//        List<RouteActions> routeActions = dbRouteActions(routeKey);
//        tailCacheService.insert(CacheKey.cacheGroupKey, CacheKey.getRouteActionsKey(routeKey), routeActions);
//        return routeActions;
//    }
//
//    /**
//     * 读取route下所有action,不含脚本,脚本单独缓存管理 db
//     */
//    private List<RouteActions> dbRouteActions(String routeKey) {
//        RouteActions param = new RouteActions();
//        param.setRouteKey(routeKey);
//        List<RouteActions> routeActions = (List<RouteActions>) mapDao.listObjectQuery(
//                "mdata.routeActionQuery.getRouteActions", param);
//        return routeActions;
//    }
//
//    /**
//     * 读取脚本,db
//     */
//    private Ascript dbScript(String scriptKey) {
//        Ascript param = new Ascript();
//        param.setScriptKey(scriptKey);
//        Ascript ascript = (Ascript) mapDao.queryDetail(
//                "mdata.routeActionQuery.getSingleScript", param);
//        return ascript;
//    }
//
//    /**
//     * 读取ent,db and cache
//     */
//    private Set<String> dbScriptPkg(String scriptKey) {
//        Ascript param = new Ascript();
//        param.setScriptKey(scriptKey);
//        List<String> pkgs = (List<String>) mapDao.listObjectQuery(
//                "mdata.routeActionQuery.getPkgList", param);
//        Set<String> set = new HashSet<String>();
//        set.addAll(pkgs);
//        return set;
//    }
//
//    /**
//     * 读取链接,db and cache
//     */
//    private List<Connection> dbCacheConnections(String routeKey) {
//        Object obj = tailCacheService.query(CacheKey.cacheGroupKey,
//                CacheKey.getRouteConnectionsKey(routeKey));
//        if (obj != null && obj instanceof List) {
//            return (List<Connection>) obj;
//        }
//        List<Connection> connections = dbConnections(routeKey);
//        tailCacheService.insert(CacheKey.cacheGroupKey, CacheKey.getRouteConnectionsKey(routeKey), connections);
//        return connections;
//    }
//
//    private List<Connection> dbConnections(String routeKey) {
//        RouteActions param = new RouteActions();
//        param.setRouteKey(routeKey);
//        List<Connection> connections = (List<Connection>) mapDao.listObjectQuery(
//                "mdata.routeConnectionsQuery.getConnections", param);
//
//        return connections;
//    }
//
////    private String versionkey() {
////        Object obj = tailCacheService.query(CacheKey.cacheGroupKey,
////                CacheKey.getVersionKey());
////        if (obj != null) {
////            return (String) obj;
////        }
////        Object objq = mapDao.queryDetail("mdata.aVersion.versionkey",new HashMap<String,String>());
////        if(objq == null){
////            return "none";
////        }
////        Map m = (Map) objq;
////        if(m.containsKey("versionKey")){
////            return m.get("versionKey").toString();
////        }
////        return "none";
////    }
//
//
//    public void setMapDao(MapDao mapDao) {
//        this.mapDao = mapDao;
//    }
//
//    public void setTailCacheService(TailCacheService tailCacheService) {
//        this.tailCacheService = tailCacheService;
//    }
//
//}
