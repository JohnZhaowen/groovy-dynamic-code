/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.exception;

import com.alipay.mcenter.core.engine.aengine.model.Action;

/**
 * 动作重试异常
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ActionRetryException extends RuntimeException {

    private static final long serialVersionUID = -9036473259772028965L;

    private Action            action;

    public ActionRetryException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param action
     */
    public ActionRetryException(String message, Action action) {
        super(message, new RuntimeException(message));
        this.action = action;
    }

    public ActionRetryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param action
     */
    public ActionRetryException(String message, Throwable cause, Action action) {
        super(message, cause);
        this.action = action;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
