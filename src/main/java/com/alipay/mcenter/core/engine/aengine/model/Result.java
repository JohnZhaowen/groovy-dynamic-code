/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.model;

/**
 * 通用执行结果
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Result {

    /**
     * 结果唯一识别,自动控制
     */
    private String    uuid;

    /**
     * 结果是否成功
     */
    private boolean   isSuccess = true;

    /**
     * judge 结果
     */
    private boolean   judge     = true;

    /**
     * 结果输出对象
     */
    private Object    outData;

    private Throwable exception;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getOutData() {
        return outData;
    }

    public void setOutData(Object outData) {
        this.outData = outData;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public void setResultFalse() {
        this.isSuccess = false;
        this.judge = false;
    }

}
