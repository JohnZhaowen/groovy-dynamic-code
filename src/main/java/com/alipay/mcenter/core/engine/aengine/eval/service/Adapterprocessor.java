/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.service;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptSource;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import com.alipay.mcenter.core.engine.aengine.temp.model.InterfaceScript;

/**
 * 脚本适配预处理服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface Adapterprocessor {
    /**
     * 脚本适配--class
     * @param script
     * @return
     */
    ScriptSource handleClass(Script script);

    /**
     * 根据模板名适配脚本--接口类
     * @param iScript
     * @param templateName
     * @return
     */
    ScriptSource handleInterface(InterfaceScript iScript, String templateName);

}
