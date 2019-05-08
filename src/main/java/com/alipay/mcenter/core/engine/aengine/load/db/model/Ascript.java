/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.load.db.model;

import com.alipay.mcenter.core.engine.aengine.load.db.model.f.BasePojo;

/**
 * script pojo
 * @author chingsung.zihong
 * @version $Id: 2017-06-08 $
 */
public class Ascript extends BasePojo {

    private String scriptKey;

    private String script;

    private String scriptType;

    public String getScriptKey() {
        return scriptKey;
    }

    public void setScriptKey(String scriptKey) {
        this.scriptKey = scriptKey;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }
}
