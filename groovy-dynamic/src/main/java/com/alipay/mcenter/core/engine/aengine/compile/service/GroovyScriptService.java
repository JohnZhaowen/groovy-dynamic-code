/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.compile.service;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import org.codehaus.groovy.tools.GroovyClass;

import java.util.List;

/**
 * groovy脚本编译装载服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public interface GroovyScriptService {

    /**
     * 按className,脚本内容编译一个class
     * @param className
     * @param source
     * @return
     */
    List<GroovyClass> compile(String className, String source);

    /**
     * 按脚本内容装载一个对象
     * @param source
     * @param <T>
     * @return
     */
    <T> ScriptObject<T> compile(String source);

    /**
     * 尝试编译装载
     * @param source
     * @return
     */
    ScriptObject tryCompile(String source);

    /**
     * 加载类
     * @param groovyClassesList
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    <T> ScriptObject<T> loadClasses(List<GroovyClass> groovyClassesList)
                                                                        throws IllegalAccessException,
                                                                        InstantiationException;

    /**
     * 按字符流加载class
     * @param className
     * @param bytes
     * @return
     */
    Object loadClass(String className, byte[] bytes) throws IllegalAccessException,
                                                    InstantiationException;

    /**
     * add class路径
     * @param classPath
     */
    void addClassPath(String classPath);

}
