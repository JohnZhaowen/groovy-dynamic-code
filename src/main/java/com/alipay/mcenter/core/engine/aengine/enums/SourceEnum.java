/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.enums;

import java.io.Serializable;

/**
 * 源
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public enum SourceEnum implements Serializable {

    /** Uniform消息入口 */
    UniformEventMessageListener("UniformEventMessageListener", "Uniform消息入口"),

    ;

    /** 说明 */
    private String code;
    private String desc;

    /**
     * CT
     */
    private SourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SourceEnum getByCode(String code) {
        for (SourceEnum e : SourceEnum.values()) {
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
