/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.model;

import com.alipay.mcenter.core.engine.aengine.acollection.model.Element;
import com.alipay.mcenter.core.engine.aengine.enums.EntityTypeEnum;

/**
 * 实体类
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Entity extends Element {

    /**
     * 实体类pkg
     */
    private String         comPath;

    /**
     * 实体类类别
     */
    private EntityTypeEnum entityType;

    /**
     * 实体类beanId
     */
    private String         beanId;

    public String getComPath() {
        return comPath;
    }

    public void setComPath(String comPath) {
        this.comPath = comPath;
    }

    public EntityTypeEnum getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypeEnum entityType) {
        this.entityType = entityType;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }
}
