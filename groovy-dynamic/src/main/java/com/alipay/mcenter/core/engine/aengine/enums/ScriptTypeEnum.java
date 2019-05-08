/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.enums;

import java.io.Serializable;

/**
 * 脚本类别枚举
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public enum ScriptTypeEnum implements Serializable {

    /** 接口类 */
    INTERFACE("INTERFACE", "接口类"),

    /** 函数类 */
    FUNC("FUNC", "函数类"),

    /** 类 */
    CLASS("CLASS", "类"),

    /** 引用 */
    INCLUDE("INCLUDE", "引用"),

    VM("VM", "VM"),
    ;

    /** 说明 */
    private String code;
    private String desc;

    /**
     * CT
     */
    private ScriptTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ScriptTypeEnum getByCode(String code) {
        for (ScriptTypeEnum e : ScriptTypeEnum.values()) {
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
