/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.model;

import com.alipay.mcenter.core.engine.aengine.acollection.model.Connection;
import com.alipay.mcenter.core.engine.aengine.acollection.model.Element;
import com.alipay.mcenter.core.engine.aengine.enums.RouteTypeEnum;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 动作流对象
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Route extends Element {

    /**
     * 动作流类别
     */
    private RouteTypeEnum             routeType;

    /**
     * 动作流的开始点
     */
    private String                    beginAc;

    /**
     * 动作流的前提表达式
     */
    private String                    cron;

    /**
     * 动作流下的动作集合
     */
    private Hashtable<String, Action> actions     = new Hashtable<String, Action>();

    /**
     * 动作流的流程集合
     */
    private List<Connection>          connections = new ArrayList<Connection>();

    public String getNextKey(String from) {
        for (Connection connection : connections) {
            if (StringUtils.equals(from, connection.getBegin())) {
                return connection.getEnd();
            }
        }
        return null;
    }

    public Action getAction(String key) {
        return actions.get(key);
    }

    public RouteTypeEnum getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteTypeEnum routeType) {
        this.routeType = routeType;
    }

    public String getBeginAc() {
        return beginAc;
    }

    public void setBeginAc(String beginAc) {
        this.beginAc = beginAc;
    }

    public Hashtable<String, Action> getActions() {
        return actions;
    }

    public void setActions(Hashtable<String, Action> actions) {
        this.actions = actions;
    }

    public List<Connection> getConnects() {
        return connections;
    }

    public void setConnects(List<Connection> connects) {
        this.connections = connects;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
