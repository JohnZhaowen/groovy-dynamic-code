///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2017 All Rights Reserved.
// */
//package com.alipay.mcenter.core.engine.aengine.load.xml.point;
//
//import com.alipay.sofa.common.xmap.annotation.XNode;
//import com.alipay.sofa.common.xmap.annotation.XNodeList;
//import com.alipay.sofa.common.xmap.annotation.XObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * xml版动作流扩展配置
// * @author chingsung.zihong
// * @version $Id: 2017-05-11 $
// */
//@XObject("route")
//public class RouteConfig {
//
//    @XNode("@source")
//    private String              source;
//
//    @XNode("@beginAg")
//    private String              beginAg;
//
//    @XNode("@cron")
//    private String              cron;
//
//    @XNode("@level")
//    private String              level;
//
//    @XNode("@env")
//    private String              env;
//
//    @XNode("@key")
//    private String              key;
//
//    @XNodeList(value = "actions/action", type = ArrayList.class, componentType = ActionConfig.class)
//    private List<ActionConfig>  actions     = new ArrayList<ActionConfig>();
//
//    @XNodeList(value = "connections/connect", type = ArrayList.class, componentType = ConnectConfig.class)
//    private List<ConnectConfig> connections = new ArrayList<ConnectConfig>();
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getBeginAg() {
//        return beginAg;
//    }
//
//    public void setBeginAg(String beginAg) {
//        this.beginAg = beginAg;
//    }
//
//    public List<ActionConfig> getActions() {
//        return actions;
//    }
//
//    public void setActions(List<ActionConfig> actions) {
//        this.actions = actions;
//    }
//
//    public List<ConnectConfig> getConnections() {
//        return connections;
//    }
//
//    public void setConnections(List<ConnectConfig> connections) {
//        this.connections = connections;
//    }
//
//    public String getCron() {
//        return cron;
//    }
//
//    public void setCron(String cron) {
//        this.cron = cron;
//    }
//
//    public String getLevel() {
//        return level;
//    }
//
//    public void setLevel(String level) {
//        this.level = level;
//    }
//
//    public String getEnv() {
//        return env;
//    }
//
//    public void setEnv(String env) {
//        this.env = env;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//}
