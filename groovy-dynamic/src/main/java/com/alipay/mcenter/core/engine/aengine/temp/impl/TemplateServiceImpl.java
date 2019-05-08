/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.temp.impl;

import com.alipay.mcenter.core.engine.aengine.temp.TemplateService;
import com.alipay.mcenter.core.engine.aengine.temp.impl.f.FtemplateService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.Map;

/**
 * @see com.alipay.mcenter.core.engine.aengine.temp.TemplateService
 */
public class TemplateServiceImpl extends FtemplateService implements TemplateService {

    /**
     * @see com.alipay.mcenter.core.engine.aengine.temp.TemplateService#getTemplate(Map, String)
     */
    @Override
    public String getTemplate(Map<String, Object> velocityContext, String templateName) {
        try {
            String ss = loadFile("Template/" + templateName + ".vm");
            Velocity.init();
            StringWriter sw = new StringWriter();

            VelocityContext context = new VelocityContext();
            for (String key : velocityContext.keySet()) {
                context.put(key, velocityContext.get(key));
            }

            Velocity.evaluate(context, sw, templateName, ss);

            return sw.toString();
        } catch (Exception e) {
            return null;
        }

    }
}
