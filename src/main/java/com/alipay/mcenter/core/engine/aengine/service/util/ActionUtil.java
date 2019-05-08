/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.util;

import com.alipay.mcenter.core.engine.aengine.enums.EnvEnum;
import com.alipay.mcenter.core.engine.aengine.enums.ScriptTypeEnum;
import com.alipay.mcenter.core.engine.aengine.model.Action;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import com.alipay.mcenter.core.engine.aengine.temp.model.InterfaceScript;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

/**
 * 动作执行处理工具类
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ActionUtil {

    /**
     * action转换InterfaceScript
     * @param action
     */
    public static InterfaceScript getiScript(Action action) {
        InterfaceScript iScript = new InterfaceScript();
        iScript.setEval(action.getScript());
        iScript.setJudge(action.getJudge());

        if (action.getScript() != null && action.getScript().getImports() != null) {
            Iterator<String> it = action.getScript().getImports().iterator();
            while (it.hasNext()) {
                String imp = it.next();
                iScript.addImports(imp);
            }
        }

        if (action.getJudge() != null && action.getJudge().getImports() != null) {
            Iterator<String> it = action.getJudge().getImports().iterator();
            while (it.hasNext()) {
                String imp = it.next();
                iScript.addImports(imp);
            }
        }

        iScript.addImports(Alog.class.getName());

        return iScript;
    }

    /**
     * script转换InterfaceScript
     * @param script
     */
    public static InterfaceScript getiScript(Script script) {
        InterfaceScript iScript = new InterfaceScript();
        iScript.setEval(script);
        if (script.getImports() != null) {
            Iterator<String> it = script.getImports().iterator();
            while (it.hasNext()) {
                String imp = it.next();
                iScript.addImports(imp);
            }
        }
        return iScript;
    }

    /**
     * action是否是Interface
     * @param action
     */
    public static Boolean isInterface(Action action) {
        if (action != null
            && action.getScript() != null
            && StringUtils.equals(ScriptTypeEnum.INTERFACE.getCode(), action.getScript()
                .getScriptType().getCode())) {
            return true;
        }

        return false;
    }

    public static Boolean isInterface(Script script) {
        if (script != null
            && StringUtils.equals(ScriptTypeEnum.INTERFACE.getCode(), script.getScriptType()
                .getCode())) {
            return true;
        }

        return false;
    }

    public static Boolean isVm(Script script) {
        if (script != null
                && StringUtils.equals(ScriptTypeEnum.VM.getCode(), script.getScriptType()
                .getCode())) {
            return true;
        }

        return false;
    }

    /**
     * 是否预发
     */
    public static boolean isPre() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            return StringUtils.contains(hostName, "-99-");
        } catch (UnknownHostException e) {
            return false;
        }
    }

    /**
     * 是否生产
     */
    public static boolean isOnline() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            return StringUtils.contains(hostName, "-66-") || isPre();
        } catch (UnknownHostException e) {
            return false;
        }
    }

    /**
     * 是否线下
     */
    public static boolean isOffline() {
        return !isOnline();
    }

    /**
     * 环境校验
     */
    public static boolean elEnv(String env) {
        if (StringUtils.isBlank(env) || "*".equalsIgnoreCase(env)) {
            return true;
        }
        String ss[] = env.split("|");
        for (int i = 0; i < ss.length; i++) {
            if (EnvEnum.offline.getCode().equalsIgnoreCase(ss[i])) {
                return isOffline();
            }
            if (EnvEnum.onlypre.getCode().equalsIgnoreCase(ss[i])) {
                return isPre();
            }
            if (EnvEnum.online.getCode().equalsIgnoreCase(ss[i])) {
                return isOnline();
            }
        }
        return false;
    }
}
