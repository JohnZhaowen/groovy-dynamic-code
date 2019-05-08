/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.compile.service.impl;

import com.alipay.mcenter.core.engine.aengine.compile.model.p.ClassBytes;
import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService;
import com.alipay.mcenter.core.engine.aengine.exception.ScriptCompileException;
import groovy.lang.GroovyClassLoader;
import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.Phases;
import org.codehaus.groovy.tools.GroovyClass;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService
 */
public class GroovyScriptServiceImpl implements GroovyScriptService, InitializingBean {

    private String            classPath             = "";

    private GroovyClassLoader baseGroovyClassLoader = new GroovyClassLoader();

    /**
     * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService#compile(String, String)
     */
    @Override
    public List<GroovyClass> compile(String className, String source) {
        try {
            List groovyClasses = compileGroovyScript(className, source);
            return groovyClasses;
        } catch (Throwable e) {
            throw new ScriptCompileException("");
        }
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService#compile(String)
     */
    @Override
    public <T> ScriptObject<T> compile(String source) {
        try {
            GroovyClassLoader groovyClassLoader = initClassLoader();
            ScriptObject<T> scriptObject = new ScriptObject<T>(source);
            Class<T> groovyClass = groovyClassLoader.parseClass(source);
            scriptObject.setCompileObject(groovyClass.newInstance());
            return scriptObject;
        } catch (InstantiationException e) {
            throw new ScriptCompileException("groovy parse error.", e);
        } catch (IllegalAccessException e) {
            throw new ScriptCompileException("groovy parse error:", e);
        }
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService#tryCompile(String)
     */
    @Override
    public ScriptObject tryCompile(String source) {
        String className = UUID.randomUUID().toString();
        className = className.replace("-", "");
        ScriptObject scriptObject = new ScriptObject(className, source);
        try {
            compileGroovyScript(this.baseGroovyClassLoader, className, source);
        } catch (Throwable e) {
            scriptObject.setException(e);
        }
        return scriptObject;
    }

    /**
     * 初始化classloader
     */
    private GroovyClassLoader initClassLoader() {
        return new GroovyClassLoader(baseGroovyClassLoader);
    }

    /**
     * 根据loader装载一个groovy脚本
     * @param className
     * @param script
     */
    private List compileGroovyScript(GroovyClassLoader loader, final String className,
                                     final String script) {
        CompilationUnit compileUnit = new CompilationUnit();
        compileUnit.setClassLoader(loader);
        compileUnit.addSource(className, script);
        compileUnit.compile(Phases.CLASS_GENERATION);
        return compileUnit.getClasses();
    }

    /**
     * 根据默认的loader装载一个groovy脚本
     * @param className
     * @param script
     */
    private List compileGroovyScript(final String className, final String script) {
        CompilationUnit compileUnit = new CompilationUnit();
        compileUnit.setClassLoader(baseGroovyClassLoader);
        compileUnit.addSource(className, script);
        compileUnit.compile(Phases.CLASS_GENERATION);
        return compileUnit.getClasses();
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService#loadClasses(List)
     */
    @Override
    public <T> ScriptObject<T> loadClasses(List<GroovyClass> groovyClassesList)
                                                                               throws IllegalAccessException,
                                                                               InstantiationException {
        ScriptObject<T> scriptObject = new ScriptObject<T>();
        GroovyClassLoader groovyClassLoader = initClassLoader();
        Class<T> newGroovyClass = groovyClassLoader.defineClass(groovyClassesList.get(0).getName(),
            groovyClassesList.get(0).getBytes());

        for (int i = 1; i < groovyClassesList.size(); i++) {
            groovyClassLoader.defineClass(groovyClassesList.get(i).getName(), groovyClassesList
                .get(i).getBytes());
        }
        T object = newGroovyClass.newInstance();
        scriptObject.setCompileObject(object);
        List<ClassBytes> classBytes = new ArrayList<ClassBytes>(groovyClassesList.size());
        for (GroovyClass groovyClass : groovyClassesList) {
            classBytes.add(new ClassBytes(groovyClass.getName(), groovyClass.getBytes()));
        }
        scriptObject.setClassBytes(classBytes);
        return scriptObject;
    }

    /**
     * loader中增加classPath
     * @param loader
     * @param classPath
     */
    private void addClassPath(GroovyClassLoader loader, String classPath) {
        if (StringUtils.isEmpty(classPath)) {
            return;
        }
        File dtodir = new File(classPath);
        if (!dtodir.exists()) {
            return;
        }
        if (dtodir.isDirectory()) {
            File[] dtos = dtodir.listFiles();
            for (File dto : dtos) {
                if (dto.isFile()) {
                    loader.addClasspath(dto.getAbsolutePath());
                } else {
                    addClassPath(loader, dto.getAbsolutePath());
                }
            }
        } else {
            loader.addClasspath(dtodir.getAbsolutePath());
        }
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService#loadClass(String, byte[])
     */
    @Override
    public Object loadClass(String className, byte[] bytes) throws IllegalAccessException,
                                                           InstantiationException {
        GroovyClassLoader innerLoader = initClassLoader();
        Class tc = innerLoader.defineClass(className, bytes);
        return tc.newInstance();
    }

    /**
     * @see com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService#addClassPath(String)
     */
    @Override
    public void addClassPath(String classPath) {
        this.baseGroovyClassLoader.addClasspath(classPath);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addClassPath(baseGroovyClassLoader, classPath);
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
