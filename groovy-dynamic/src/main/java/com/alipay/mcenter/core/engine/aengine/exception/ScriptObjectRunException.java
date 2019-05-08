/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.exception;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.model.Result;

/**
 * 脚本执行异常
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ScriptObjectRunException extends RuntimeException {

    private static final long serialVersionUID = -9026473259772028965L;

    private ScriptObject      scriptObject;

    private Result            result;

    public ScriptObjectRunException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param scriptObject
     */
    public ScriptObjectRunException(String message, ScriptObject scriptObject) {
        super(message, new RuntimeException(message));
        this.scriptObject = scriptObject;
    }

    public ScriptObjectRunException(String message, ScriptObject scriptObject, Result result) {
        super(message, new RuntimeException(message));
        this.scriptObject = scriptObject;
        this.result = result;
    }

    public ScriptObjectRunException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param scriptObject
     */
    public ScriptObjectRunException(String message, Throwable cause, ScriptObject scriptObject) {
        super(message, cause);
        this.scriptObject = scriptObject;
    }

    public ScriptObjectRunException(String message, Throwable cause, ScriptObject scriptObject,
                                    Result result) {
        super(message, cause);
        this.scriptObject = scriptObject;
        this.result = result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ScriptObject getScriptObject() {
        return scriptObject;
    }

    public void setScriptObject(ScriptObject scriptObject) {
        this.scriptObject = scriptObject;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
