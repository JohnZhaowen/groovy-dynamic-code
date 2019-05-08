/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.model;

import com.alipay.mcenter.core.engine.aengine.acollection.model.Element;
import com.alipay.mcenter.core.engine.aengine.enums.ScriptTypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * 脚本对象
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Script extends Element {

    /**
     * 脚本正文
     */
    private String         scriptText;

    /**
     * 脚本的引用类pkg
     */
    private Set<String>    imports;

    /**
     * 脚本类别
     */
    private ScriptTypeEnum scriptType;

    /**
     * 增加一个引入
     * @param impt
     */
    public void addImports(String impt) {
        if (imports == null) {
            imports = new HashSet<String>();
        }
        imports.add(impt);
    }

    public String getScriptText() {
        return scriptText;
    }

    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public ScriptTypeEnum getScriptType() {
        return scriptType;
    }

    public void setScriptType(ScriptTypeEnum scriptType) {
        this.scriptType = scriptType;
    }
}
