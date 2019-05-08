/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.service.impl;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptSource;
import com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService;
import com.alipay.mcenter.core.engine.aengine.enums.ScriptTypeEnum;
import com.alipay.mcenter.core.engine.aengine.eval.service.Adapterprocessor;
import com.alipay.mcenter.core.engine.aengine.eval.service.ScriptEngineService;
import com.alipay.mcenter.core.engine.aengine.exception.ScriptCompileException;
import com.alipay.mcenter.core.engine.aengine.temp.model.FuncScript;
import com.alipay.mcenter.core.engine.aengine.temp.model.InterfaceScript;

/**
 * 模型为groovy的脚本处理引擎服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class GroovyScriptEngineImpl implements ScriptEngineService {

    private GroovyScriptService groovyScriptService;

    private Adapterprocessor    adapterGroovy;

    /**
     * @see com.alipay.mcenter.core.engine.aengine.eval.service.ScriptEngineService#preFuncProcessor(FuncScript)
     */
    @Override
    public ScriptSource preFuncProcessor(FuncScript fScript) {
        return null;
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.eval.service.ScriptEngineService#compileFunction(FuncScript)
     */
    @Override
    public ScriptObject<Object> compileFunction(FuncScript fScript) {
        ScriptSource ss = new ScriptSource();
        try {
            ss = preFuncProcessor(fScript);
            ScriptObject scriptObject = groovyScriptService.loadClasses(groovyScriptService
                .compile(ss.getClassName(), ss.getSourceCode()));
            scriptObject.setClassName(ss.getClassName());
            scriptObject.setSource(ss.getSourceCode());
            return scriptObject;
        } catch (Throwable e) {
            throw new ScriptCompileException("");
        }
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.eval.service.ScriptEngineService#preInterfaceProcessor(InterfaceScript)
     */
    @Override
    public ScriptSource preInterfaceProcessor(InterfaceScript iScript) {
        if (null == adapterGroovy) {
            throw new IllegalArgumentException("no preprocessor in script context. bug!");
        }
        return adapterGroovy.handleInterface(iScript, "interfaceCommonScriptTemplate");
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.eval.service.ScriptEngineService#compileInterface(InterfaceScript, Class)
     */
    @Override
    public <T> ScriptObject<T> compileInterface(InterfaceScript iScript, Class<T> interfaze) {
        iScript.getEval().setScriptType(ScriptTypeEnum.INTERFACE);
        ScriptSource ss = preInterfaceProcessor(iScript);
        String className = ss.getPackageName() + "." + ss.getClassName();
        ScriptObject<T> scriptObject = new ScriptObject<T>();
        try {
            scriptObject = groovyScriptService.loadClasses(groovyScriptService.compile(className,
                ss.getSourceCode()));
        } catch (Exception e) {
            throw new ScriptCompileException("");
        }
        scriptObject.setSource(ss.getSourceCode());
        scriptObject.setClassName(className);
        return scriptObject;
    }

    public void setGroovyScriptService(GroovyScriptService groovyScriptService) {
        this.groovyScriptService = groovyScriptService;
    }

    public void setAdapterGroovy(Adapterprocessor adapterGroovy) {
        this.adapterGroovy = adapterGroovy;
    }
}
