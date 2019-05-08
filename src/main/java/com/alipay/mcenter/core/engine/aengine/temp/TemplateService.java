/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.temp;

import java.util.Map;

/**
 * 支持语言的模本服务,模板为vm文件
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface TemplateService {

    /**
     * 获得填充后的模本内容
     * @param velocityContext
     * @param templateName
     */
    String getTemplate(Map<String, Object> velocityContext, String templateName);

}
