/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.enums;

import java.io.Serializable;

/**
 * 动作类别枚举
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public enum ActionTypeEnum implements Serializable {

    /** 存储类动作 */
    STORE("STORE", "存储类动作"),

    /** 查询 */
    SEL("SEL", "查询"),

    /** 脚本类动作 */
    SCRIPT("SCRIPT", "脚本类动作"),

    ;

    /** 说明 */
    private String code;
    private String desc;

    /**
     * CT
     */
    private ActionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ActionTypeEnum getByCode(String code) {
        for (ActionTypeEnum e : ActionTypeEnum.values()) {
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
