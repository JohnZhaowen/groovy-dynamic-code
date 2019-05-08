/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.model;

import com.alipay.mcenter.core.engine.aengine.acollection.model.Element;

/**
 * 函数对象
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Func extends Element {

    /**
     * 函数名称,全局唯一
     */
    private String funcName;

    /**
     * 函数对应实体类
     */
    private Entity funcEty;

    /**
     * 函数输入对象
     */
    private Object input;

    /**
     * 函数执行结果
     */
    private Result result;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Entity getFuncEty() {
        return funcEty;
    }

    public void setFuncEty(Entity funcEty) {
        this.funcEty = funcEty;
    }
}
