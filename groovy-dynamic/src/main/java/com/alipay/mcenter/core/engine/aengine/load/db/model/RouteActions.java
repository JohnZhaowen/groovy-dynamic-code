/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.model;

import com.alipay.mcenter.core.engine.aengine.load.db.model.f.BasePojo;

/**
 * route-action pojo
 * @author chingsung.zihong
 * @version $Id: 2017-06-08 $
 */
public class RouteActions extends BasePojo {

    private String  routeKey;

    private String  routeType;

    private String  begin;

    private String  acron;

    private String  level;

    private String  actionKey;

    private String  actionType;

    private String  ascript;

    private String  judge;

    private Integer isMust;

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getAcron() {
        return acron;
    }

    public void setAcron(String acron) {
        this.acron = acron;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAscript() {
        return ascript;
    }

    public void setAscript(String ascript) {
        this.ascript = ascript;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public Integer getIsMust() {
        return isMust;
    }

    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
}
