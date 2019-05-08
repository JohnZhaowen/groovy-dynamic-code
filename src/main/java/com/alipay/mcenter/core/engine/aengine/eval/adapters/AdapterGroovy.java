/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.adapters;

import com.alipay.mcenter.core.engine.aengine.temp.TemplateService;
import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptSource;
import com.alipay.mcenter.core.engine.aengine.eval.service.Adapterprocessor;
import com.alipay.mcenter.core.engine.aengine.eval.util.ClassNameHelper;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import com.alipay.mcenter.core.engine.aengine.temp.model.InterfaceScript;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @see com.alipay.mcenter.core.engine.aengine.eval.service.Adapterprocessor
 */
public class AdapterGroovy implements Adapterprocessor {

    public final static String DEFAULT_PACKAGE_NAME = "com.alipay.mcenter.core.engine.aengine.classes";

    public final static String CLASS_NAME           = "zihong_groovy_";

    private TemplateService    templateService;

    /**
     * @see com.alipay.mcenter.core.engine.aengine.eval.service.Adapterprocessor#handleClass(Script)
     */
    @Override
    public ScriptSource handleClass(Script script) {
        return null;
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.eval.service.Adapterprocessor#handleInterface(InterfaceScript, String)
     */
    @Override
    public ScriptSource handleInterface(InterfaceScript iScript, String templateName) {
        Map<String, Object> context = new HashMap<String, Object>();
        ScriptSource scriptSource = new ScriptSource();
        scriptSource.setPackageName(DEFAULT_PACKAGE_NAME);
        scriptSource.setClassName(CLASS_NAME + ClassNameHelper.generator());

        context.put("package", scriptSource.getPackageName());
        context.put("className", scriptSource.getClassName());
        context.put("imports", iScript.getImports());
        context.put("script", iScript.getEval().getScriptText().replace("@and", "").replace("@",""));
        if (iScript.getJudge() != null && StringUtils.isNotBlank(iScript.getJudge().getScriptText())) {
            context.put("judge", iScript.getJudge().getScriptText().replace("@and", "").replace("@",""));
        } else {
            context.put("judge", "return true;");
        }
        String adapterSource = templateService.getTemplate(context, templateName);

        scriptSource.setSourceCode(adapterSource);
        return scriptSource;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
