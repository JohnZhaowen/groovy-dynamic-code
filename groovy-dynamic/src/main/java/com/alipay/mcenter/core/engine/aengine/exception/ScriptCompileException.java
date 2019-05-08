/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.exception;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptSource;

/**
 * 脚本编译异常
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ScriptCompileException extends RuntimeException {

    private static final long serialVersionUID = -9016473259772028965L;

    private ScriptSource      scriptSource;

    public ScriptCompileException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param scriptSource
     */
    public ScriptCompileException(String message, ScriptSource scriptSource) {
        super(message, new RuntimeException(message));
        this.scriptSource = scriptSource;
    }

    public ScriptCompileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param scriptSource
     */
    public ScriptCompileException(String message, Throwable cause, ScriptSource scriptSource) {
        super(message, cause);
        this.scriptSource = scriptSource;
    }

    public ScriptSource getScriptSource() {
        return scriptSource;
    }

    public void setScriptSource(ScriptSource scriptSource) {
        this.scriptSource = scriptSource;
    }
}
