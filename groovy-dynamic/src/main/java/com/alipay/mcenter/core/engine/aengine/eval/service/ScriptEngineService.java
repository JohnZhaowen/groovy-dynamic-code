/*
  Alipay.com Inc.
  Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.service;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptSource;
import com.alipay.mcenter.core.engine.aengine.temp.model.FuncScript;
import com.alipay.mcenter.core.engine.aengine.temp.model.InterfaceScript;

/**
 * 脚本处理引擎服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface ScriptEngineService {

    /**
     * 接口类脚本适配预处理
     * @param iScript
     * @return
     */
    ScriptSource preInterfaceProcessor(InterfaceScript iScript);

    /**
     * 装载接口类脚本
     * @param iScript
     * @param interfaze
     * @return
     */
    <T> ScriptObject<T> compileInterface(InterfaceScript iScript, Class<T> interfaze);

    /**
     * 函数类脚本适配预处理
     * @param fScript
     * @return
     */
    ScriptSource preFuncProcessor(FuncScript fScript);

    /**
     * 装载函数类脚本
     * @param fScript
     * @return
     */
    ScriptObject<Object> compileFunction(FuncScript fScript);

}
