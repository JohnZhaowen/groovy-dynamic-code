/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.enums;

import java.io.Serializable;

/**
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public enum EnvEnum implements Serializable {

    /** 线下 */
    offline("offline", "线下"),

    onlypre("onlypre", "预发-99-"),

    online("online", "生产,包括预发"),

    ;

    /** 说明 */
    private String code;
    private String desc;

    /**
     * CT
     */
    private EnvEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EnvEnum getByCode(String code) {
        for (EnvEnum e : EnvEnum.values()) {
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
