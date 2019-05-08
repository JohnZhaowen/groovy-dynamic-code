///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2017 All Rights Reserved.
// */
//package com.alipay.mcenter.core.engine.aengine.load.xml.service.impl;
//
//import com.alibaba.common.lang.StringUtil;
//import com.alipay.mcenter.core.engine.aengine.acollection.model.Connection;
//import com.alipay.mcenter.core.engine.aengine.enums.ActionTypeEnum;
//import com.alipay.mcenter.core.engine.aengine.enums.ScriptTypeEnum;
//import com.alipay.mcenter.core.engine.aengine.load.xml.point.ActionConfig;
//import com.alipay.mcenter.core.engine.aengine.load.xml.point.ConnectConfig;
//import com.alipay.mcenter.core.engine.aengine.load.xml.point.RouteConfig;
//import com.alipay.mcenter.core.engine.aengine.load.xml.service.XmlRouteLoadService;
//import com.alipay.mcenter.core.engine.aengine.model.Action;
//import com.alipay.mcenter.core.engine.aengine.model.Route;
//import com.alipay.mcenter.core.engine.aengine.model.Script;
//import com.alipay.mcenter.core.engine.aengine.service.util.ActionUtil;
//import com.alipay.sofa.service.api.component.Extension;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @see com.alipay.mcenter.core.engine.aengine.load.xml.service.XmlRouteLoadService
// */
//public class XmlRouteLoadServiceImpl implements XmlRouteLoadService {
//
//    /**
//     * sofa扩展注入的route配置
//     */
//    private Map<String, RouteConfig> routeConfigs = new HashMap<String, RouteConfig>();
//
//    /**
//     * @see com.alipay.mcenter.core.engine.aengine.load.xml.service.XmlRouteLoadService#loadBySource(String)
//     */
//    @Override
//    public Route loadBySource(String source) {
//        RouteConfig routeConfig = routeConfigs.get(source);
//        Route route = new Route();
//        String env = routeConfig.getEnv();
//        if(StringUtil.isNotBlank(env) && ActionUtil.elEnv(env)){
//            return route;
//        }
//        List<ActionConfig> actions = routeConfig.getActions();
//        List<ConnectConfig> connections = routeConfig.getConnections();
//
//        for (ActionConfig ac : actions) {
//
//            Action action = new Action();
//            action.setKey(ac.getKey());
//            action.setType(ActionTypeEnum.getByCode(ac.getKey()));
//            Script script = new Script();
//
//            script.setScriptType(ScriptTypeEnum.getByCode(ac.getStype()));
//            script.setScriptText(ac.getScript());
//            script.setScriptType(ScriptTypeEnum.INTERFACE);
//            action.setScript(script);
//
//            if (ac.getImpts() != null) {
//                for (String impt : ac.getImpts()) {
//                    script.addImports(impt);
//                }
//            }
//
//            if (StringUtil.isNotBlank(ac.getJudge())) {
//                Script judge = new Script();
//                judge.setScriptType(ScriptTypeEnum.INTERFACE);
//                judge.setScriptText(ac.getJudge());
//                action.setJudge(judge);
//            }
//
//            action.setMust(ac.getMust());
//            route.getActions().put(action.getKey(), action);
//        }
//
//        for (ConnectConfig cc : connections) {
//            route.getConnects().add(new Connection(cc.getFrom(), cc.getTo()));
//        }
//
//        route.setBeginAc(routeConfig.getBeginAg());
//        route.setCron(routeConfig.getCron());
//        route.setKey(routeConfig.getLevel());
//
//        return route;
//    }
//
//    /**
//     * sofa扩展注入注册
//     */
//    public void registerExtension(Extension extension) throws Exception {
//        Object[] contributions = extension.getContributions();
//        for (int i = 0; i < contributions.length; i++) {
//            if (contributions[i] instanceof RouteConfig) {
//                RouteConfig routeConfig = (RouteConfig) contributions[i];
//                routeConfigs.put(routeConfig.getSource(), routeConfig);
//            }
//        }
//    }
//}
