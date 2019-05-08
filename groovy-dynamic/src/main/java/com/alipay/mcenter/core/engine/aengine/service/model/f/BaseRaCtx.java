/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.model.f;

/**
 * 执行动作route的上下文
 * @author chingsung.zihong
 * @version $Id: 2017-06-05 $
 */
public class BaseRaCtx {

    public static final String beanservicekey = "BEAN_F_SERVICE";

    private String source;

    private String route;

    private String action;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
