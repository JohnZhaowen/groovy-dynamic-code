/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.enums;

import java.io.Serializable;

/**
 * 实体类别枚举
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public enum EntityTypeEnum implements Serializable {

    /** sofa的bean对象 */
    SOFABEAN("SOFA_BEAN", "sofa的bean对象"),

    /** 静态类 */
    STATIC("STATIC", "静态类"),

    /** 引用类 */
    IMPOTS("IMPOTS", "引用类"),

    ;

    /** 说明 */
    private String code;
    private String desc;

    /**
     * CT
     */
    private EntityTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EntityTypeEnum getByCode(String code) {
        for (EntityTypeEnum e : EntityTypeEnum.values()) {
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
