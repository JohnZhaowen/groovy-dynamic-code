/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.impl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.lang.reflect.Method;

/**
 * 通过实现BeanFactoryAware获得配置的bean服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class BeanFactoryService implements BeanFactoryAware {

    private BeanFactory beanFactory;

    /**
     * 通过配置bean的id获得bean对象
     * @param beanId
     */
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }

    /**
     * 通过配置bean的id获得bean对象,并且反射执行对象方法
     * @param beanId
     */
    public Object callBeanMethod(String beanId, String methodName,Object[] argsTemp) {
        try {
            return invokeMethod(beanFactory.getBean(beanId),methodName,argsTemp);
        }catch (Throwable t){
            return null;
        }
    }

    private Object invokeMethod(Object newObj, String methodName, Object[] args)throws Exception {
        Class ownerClass = newObj.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(newObj, args);
    }

    /**
     * @see BeanFactoryAware#setBeanFactory(BeanFactory)
     */
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
