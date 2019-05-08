/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.impl;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.eval.service.ScriptEngineService;
import com.alipay.mcenter.core.engine.aengine.eval.service.TextScriptEngineService;
import com.alipay.mcenter.core.engine.aengine.exception.ScriptObjectRunException;
import com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteCacheClearService;
import com.alipay.mcenter.core.engine.aengine.load.db.service.DbRouteLoadService;
import com.alipay.mcenter.core.engine.aengine.model.Action;
import com.alipay.mcenter.core.engine.aengine.model.Result;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import com.alipay.mcenter.core.engine.aengine.service.ActionService;
import com.alipay.mcenter.core.engine.aengine.service.f.RouteAction;
import com.alipay.mcenter.core.engine.aengine.service.model.RouteContext;
import com.alipay.mcenter.core.engine.aengine.service.util.ActionUtil;
import com.alipay.mcenter.core.engine.aengine.temp.groovyservice.CommonScript;
import com.alipay.mcenter.core.engine.aengine.temp.model.InterfaceScript;

import java.util.Map;

/**
 * @see com.alipay.mcenter.core.engine.aengine.service.ActionService
 */
public class ActionServiceImpl extends RouteAction implements ActionService {

    private ScriptEngineService      scriptEngineService;

    private BeanFactoryService       beanFactoryService;

    private DbRouteLoadService       dbRouteLoadService;

    private TextScriptEngineService  textScriptEngineService;

    private DbRouteCacheClearService dbRouteCacheClearService;

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.ActionService#callAction(Action, Object)
     */
    @Override
    public Result callAction(Action action, Object input) {
        RouteContext ctx = initContext(beanFactoryService);
        return callActionScript(ctx, action, input);
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.ActionService#callScript(Script, Object)
     */
    @Override
    public Result callScript(Script script, Object input) {
        RouteContext ctx = initContext(beanFactoryService);
        return callScriptOnly(ctx, script, input);
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.ActionService#callScript(String, Object)
     */
    @Override
    public Result callScript(String scriptKey, Object input) {
        RouteContext ctx = initContext(beanFactoryService);
        Script script = dbRouteLoadService.loadScript(scriptKey);
        return callScriptOnly(ctx, script, input);
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.service.ActionService#callAction(Action, RouteContext)
     */
    @Override
    public Result callAction(Action action, RouteContext ctx) {
        return callActionScript(ctx, action, ctx.getInvars(action.getKey()));
    }

    @Override
    public Boolean clearCache(String key,Map param) {
        Boolean r = dbRouteCacheClearService.clearSource(key);
        return r;
    }

    /**
     * 根据上下文,脚本,输入对象执行脚本,返回执行结果
     * @param ctx
     * @param action
     * @param input
     */
    private Result callActionScript(RouteContext ctx, Action action, Object input) {
        ScriptObject<CommonScript> scriptObject = ScriptObjectCacheService.getInstance()
                .getScriptObject(action.getKey());
        if (scriptObject == null) {
            if (ActionUtil.isInterface(action)) {

                InterfaceScript iScript = ActionUtil.getiScript(action);
                scriptObject = scriptEngineService.compileInterface(iScript, CommonScript.class);
                ScriptObjectCacheService.getInstance().cacheScriptObject(action.getKey(),
                        scriptObject);
            }
        }

        Result result = new Result();
        try {
            if (ActionUtil.isInterface(action)) {
                try {
                    Boolean judge = scriptObject.getCompileObject().judge(ctx, input);
                    if (!judge) {
                        result.setResultFalse();
                        return result;
                    }
                } catch (Exception e) {
                    result.setResultFalse();
                    throw new ScriptObjectRunException("");
                }
                Object object = scriptObject.getCompileObject().eval(ctx, input);
                result.setOutData(object);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            throw new ScriptObjectRunException("");
        }
        return result;
    }

    private Result callScriptOnly(RouteContext ctx, Script script, Object input) {
        Result result = new Result();

        if (ActionUtil.isVm(script)) {
            try {
                if (input instanceof Map) {
                    Map<String, Object> m = (Map) input;
                    String svm = textScriptEngineService.compileEvalVM(script, m);
                    svm = svm.replace("\r", "").replace("\n", "");
                    result.setOutData(svm);
                }
            } catch (Throwable t) {
                result.setSuccess(false);
                result.setException(t);
            }
        }

        if (ActionUtil.isInterface(script)) {

            InterfaceScript iScript = ActionUtil.getiScript(script);

            ScriptObject<CommonScript> scriptObject = scriptEngineService.compileInterface(iScript,
                    CommonScript.class);
            ScriptObjectCacheService.getInstance().cacheScriptObject(script.getKey(), scriptObject);

            try {
                if (ActionUtil.isInterface(script)) {
                    Object object = scriptObject.getCompileObject().eval(ctx, input);
                    result.setOutData(object);
                }
            } catch (Throwable e) {
                result.setSuccess(false);
                throw new ScriptObjectRunException("");
            }
        }

        return result;
    }

    public void setScriptEngineService(ScriptEngineService scriptEngineService) {
        this.scriptEngineService = scriptEngineService;
    }

    public void setBeanFactoryService(BeanFactoryService beanFactoryService) {
        this.beanFactoryService = beanFactoryService;
    }

    public void setDbRouteLoadService(DbRouteLoadService dbRouteLoadService) {
        this.dbRouteLoadService = dbRouteLoadService;
    }

    public void setTextScriptEngineService(TextScriptEngineService textScriptEngineService) {
        this.textScriptEngineService = textScriptEngineService;
    }

    public void setDbRouteCacheClearService(DbRouteCacheClearService dbRouteCacheClearService) {
        this.dbRouteCacheClearService = dbRouteCacheClearService;
    }
}
