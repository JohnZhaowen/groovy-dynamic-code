/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.enums;

import java.io.Serializable;

/**
 * route类别枚举
 * @author chingsung.zihong
 * @version $Id: 2017-06-08 $
 */
public enum RouteTypeEnum implements Serializable {

    /** 业务清洗 */
    ETL("ETL", "业务清洗"),

    /** 切入调用 */
    POINT("POINT", "切入调用"), ;

    /** 说明 */
    private String code;
    private String desc;

    /**
     * CT
     */
    private RouteTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RouteTypeEnum getByCode(String code) {
        for (RouteTypeEnum e : RouteTypeEnum.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
