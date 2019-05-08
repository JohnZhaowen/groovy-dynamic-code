/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.compile.model.p;

/**
 * 字节流class
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ClassBytes {

    /**
     * class的名称
     */
    private String className;

    /**
     * 字节流
     */
    private byte[] classBytes;

    public ClassBytes() {
    }

    public ClassBytes(String className, byte[] classBytes) {
        this.className = className;
        this.classBytes = classBytes;

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public byte[] getClassBytes() {
        return classBytes;
    }

    public void setClassBytes(byte[] classBytes) {
        this.classBytes = classBytes;
    }
}