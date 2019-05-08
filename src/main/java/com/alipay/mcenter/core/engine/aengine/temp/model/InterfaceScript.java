/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.temp.model;

import com.alipay.mcenter.core.engine.aengine.model.Script;

import java.util.HashSet;
import java.util.Set;

/**
 * interfaceCommonScriptTemplate.vm 模本
 * @author chingsung.zihong
 * @version $Id: 2017-06-01 $
 */
public class InterfaceScript {

    /**
     * 脚本eval
     */
    private Script      eval;

    /**
     * 脚本judge
     */
    private Script      judge;

    /**
     * class引入
     */
    private Set<String> imports;

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

    public Script getEval() {
        return eval;
    }

    public void setEval(Script eval) {
        this.eval = eval;
    }

    public Script getJudge() {
        return judge;
    }

    public void setJudge(Script judge) {
        this.judge = judge;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }
}
