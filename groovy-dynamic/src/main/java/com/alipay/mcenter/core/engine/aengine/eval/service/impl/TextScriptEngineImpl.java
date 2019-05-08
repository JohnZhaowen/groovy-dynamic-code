/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.service.impl;

import com.alipay.mcenter.core.engine.aengine.eval.service.TextScriptEngineService;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Map;

/**
 * 模型为groovy的脚本处理引擎服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class TextScriptEngineImpl implements TextScriptEngineService {

    @Override
    public String compileEvalVM(Script script, Map<String,Object> input) {

        try {
            Velocity.init();
            StringWriter sw = new StringWriter();

            VelocityContext context = new VelocityContext();
            for (String key : input.keySet()) {
                context.put(key, input.get(key));
            }

            Velocity.evaluate(context, sw, "vm", script.getScriptText());

            return sw.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
