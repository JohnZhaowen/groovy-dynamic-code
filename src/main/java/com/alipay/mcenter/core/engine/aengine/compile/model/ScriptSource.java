/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.compile.model;

/**
 * 编译脚本源码信息
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ScriptSource {

    /**
     * pkg名
     */
    private String packageName;

    /**
     * class名
     */
    private String className;

    /**
     * 源码,可以直接编译加载的
     */
    private String sourceCode;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

}
