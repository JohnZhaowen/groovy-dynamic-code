/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.model;

import com.alipay.mcenter.core.engine.aengine.load.db.model.f.BasePojo;

/**
 * source-route pojo
 * @author chingsung.zihong
 * @version $Id: 2017-06-08 $
 */
public class SourceRoute extends BasePojo {

    private String sourceKey;

    private String routeKey;

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
}
