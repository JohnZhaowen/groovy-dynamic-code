/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.model;

import com.alipay.mcenter.core.engine.aengine.acollection.model.Element;
import com.alipay.mcenter.core.engine.aengine.enums.ActionTypeEnum;

/**
 * 动作对象
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Action extends Element {

    /**
     * 动作类别
     */
    private ActionTypeEnum type;

    /**
     * 动作下挂脚本
     */
    private Script         script;

    /**
     * 前置判断
     */
    private Script         judge;

    private Boolean        must = true;

    public ActionTypeEnum getType() {
        return type;
    }

    public void setType(ActionTypeEnum type) {
        this.type = type;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public Script getJudge() {
        return judge;
    }

    public void setJudge(Script judge) {
        this.judge = judge;
    }

    public Boolean getMust() {
        return must;
    }

    public void setMust(Boolean must) {
        this.must = must;
    }
}
