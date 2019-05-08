///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2017 All Rights Reserved.
// */
//package com.alipay.mcenter.core.engine.aengine.load.xml.point;
//
//import com.alipay.sofa.common.xmap.annotation.XNode;
//import com.alipay.sofa.common.xmap.annotation.XNodeList;
//import com.alipay.sofa.common.xmap.annotation.XObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * xml版动作扩展配置
// * @author chingsung.zihong
// * @version $Id: 2017-05-11 $
// */
//@XObject
//public class ActionConfig {
//
//    /**
//     * 默认构造函数
//     */
//    public ActionConfig() {
//        super();
//    }
//
//    public ActionConfig(String key, String type, String script) {
//        super();
//        this.key = key;
//        this.type = type;
//        this.script = script;
//    }
//
//    @XNode("@key")
//    private String       key;
//
//    @XNode(value = "script")
//    private String       script;
//
//    @XNode("@type")
//    private String       type;
//
//    @XNode("@stype")
//    private String       stype;
//
//    @XNode(value = "judge")
//    private String       judge;
//
//    @XNode("@must")
//    private Boolean      must  = true;
//
//    @XNodeList(value = "impts/impt", type = ArrayList.class, componentType = String.class)
//    private List<String> impts = new ArrayList<String>();
//
//    public String getScript() {
//        return script;
//    }
//
//    public void setScript(String script) {
//        this.script = script;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public List<String> getImpts() {
//        return impts;
//    }
//
//    public void setImpts(List<String> impts) {
//        this.impts = impts;
//    }
//
//    public String getJudge() {
//        return judge;
//    }
//
//    public void setJudge(String judge) {
//        this.judge = judge;
//    }
//
//    public Boolean getMust() {
//        return must;
//    }
//
//    public void setMust(Boolean must) {
//        this.must = must;
//    }
//
//    public String getStype() {
//        return stype;
//    }
//
//    public void setStype(String stype) {
//        this.stype = stype;
//    }
//}
