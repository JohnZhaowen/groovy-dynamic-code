/*
  Alipay.com Inc.
  Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.service;

import com.alipay.mcenter.core.engine.aengine.model.Script;

import java.util.Map;

/**
 * 脚本处理引擎服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface TextScriptEngineService {

    /**
     * vm
     * @param script
     * @return
     */
    String compileEvalVM(Script script, Map<String,Object> input);

}
