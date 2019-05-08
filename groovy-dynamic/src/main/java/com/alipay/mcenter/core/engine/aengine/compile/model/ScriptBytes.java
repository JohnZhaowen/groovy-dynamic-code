/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.compile.model;

import com.alipay.mcenter.core.engine.aengine.compile.model.p.ClassBytes;


/**
 * 脚本字节流
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ScriptBytes extends ClassBytes {

    /**
     * 脚本id
     */
    private int scriptId;

    /**
     * 脚本type
     */
    private String scriptType;

    /**
     * 脚本的MD5值
     */
    private String checkSum;
    
    /**is main class, 0: false, 1:true, default 0*/
    private int isMainClass = 0;

    public int getScriptId() {
        return scriptId;
    }

    public void setScriptId(int scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public int getIsMainClass() {
        return isMainClass;
    }

    public void setIsMainClass(int isMainClass) {
        this.isMainClass = isMainClass;
    }
}
