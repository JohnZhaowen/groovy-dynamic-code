/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.model;

import com.alipay.mcenter.core.engine.aengine.model.Result;
import com.alipay.mcenter.core.engine.aengine.service.impl.BeanFactoryService;
import com.alipay.mcenter.core.engine.aengine.service.model.f.BaseRaCtx;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 执行动作route的上下文
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class RouteContext extends BaseRaCtx {

    static final private String invars_head = "CS_RC_IV";

    static final private String param_head = "CS_RC_PA";

    private String ctxId;

    public RouteContext() {
        String ctxId = StringUtils.remove(UUID.randomUUID().toString(), "-");
        this.ctxId = ctxId;
    }

    /**
     * 每个动作的输入对象,key为动作的key
     */
    private Map<String, Object> invars = new HashMap<String, Object>();

    /**
     * route使用的服务对象
     */
    private Map<String, Object> param = new HashMap<String, Object>();

    /**
     * 每个动作的执行结果对象
     */
    private Map<String, Result> actionExcuteInfo = new HashMap<String, Result>();

    /**
     * 获得spring bean对象
     */
    public Object getBean(String bean) {
        BeanFactoryService beanFactoryService = (BeanFactoryService) this.getParam(beanservicekey);
        if (beanFactoryService != null) {
            return beanFactoryService.getBean(bean);
        }
        return null;
    }

    public Object callBeanMethod(String beanId, String methodName, Object[] argsTemp) {
        BeanFactoryService beanFactoryService = (BeanFactoryService) this.getParam(beanservicekey);
        if (beanFactoryService != null) {
            return beanFactoryService.callBeanMethod(beanId, methodName, argsTemp);
        }
        return null;
    }

    public Object getInvars(String key) {
        return invars.get(invars_head + key);
    }

    public void putInvars(String key, Object object) {
        invars.put(invars_head + key, object);
    }

    public Object getParam(String key) {
        return param.get(param_head + key);
    }

    public Boolean containsParam(String key) {
        return param.containsKey(param_head + key);
    }

    public Boolean containsInvars(String key) {
        return param.containsKey(invars_head + key);
    }

    public void putParam(String key, Object object) {
        param.put(param_head + key, object);
    }

    public Result getActionExcuteInfo(String key) {
        return actionExcuteInfo.get(key);
    }

    public void putActionExcuteInfo(String key, Result object) {
        actionExcuteInfo.put(key, object);
    }

    public String getCtxId() {
        return ctxId;
    }
}
