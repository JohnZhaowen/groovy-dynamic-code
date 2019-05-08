/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.temp.model;

import com.alipay.mcenter.core.engine.aengine.model.Func;
import com.alipay.mcenter.core.engine.aengine.model.Script;

import java.util.HashSet;
import java.util.Set;

/**
 * 静态类函数 模本
 * @author chingsung.zihong
 * @version $Id: 2017-06-01 $
 */
public class FuncScript {

    /**
     * 静态类主体
     */
    private Func        func;

    /**
     * 各个静态方法
     */
    private Set<Script> scripts;

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

    public Set<Script> getScripts() {
        return scripts;
    }

    public void setScripts(Set<Script> scripts) {
        this.scripts = scripts;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public Func getFunc() {
        return func;
    }

    public void setFunc(Func func) {
        this.func = func;
    }
}
