/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.compile.model;

import com.alipay.mcenter.core.engine.aengine.compile.model.p.ClassBytes;

import java.util.List;

/**
 * 装载后脚本对象
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ScriptObject<T> {

    /**
     * 脚本名
     */
    private String           className;

    /**
     * 脚本文本
     */
    private String           source;

    /**
     * 编译装载后对象
     */
    private T                compileObject;

    /**
     * 编译装载异常
     */
    private Throwable        exception;

    /**
     * 字节流
     */
    private List<ClassBytes> classBytes;

    public ScriptObject() {

    }

    public ScriptObject(String source) {
        this.source = source;
    }

    public ScriptObject(String className, String source) {
        this.className = className;
        this.source = source;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public T getCompileObject() {
        return compileObject;
    }

    public void setCompileObject(T compileObject) {
        this.compileObject = compileObject;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public List<ClassBytes> getClassBytes() {
        return classBytes;
    }

    public void setClassBytes(List<ClassBytes> classBytes) {
        this.classBytes = classBytes;
    }
}
