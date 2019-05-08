/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.service.impl;

import com.alipay.mcenter.core.engine.aengine.cache.ram.SimpleRamCache;
import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.temp.groovyservice.CommonScript;

/**
 * ScriptObject内存缓存
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class ScriptObjectCacheService {

    private static ScriptObjectCacheService                    scriptObjectCacheService;

    private SimpleRamCache<String, ScriptObject<CommonScript>> simpleRamCache = new SimpleRamCache<String, ScriptObject<CommonScript>>(
                                                                                  100000);

    private ScriptObjectCacheService() {
    }

    public static ScriptObjectCacheService getInstance() {
    	
    	if(scriptObjectCacheService == null) {
    		synchronized (ScriptObjectCacheService.class) {
                if (scriptObjectCacheService == null) {
                    scriptObjectCacheService = new ScriptObjectCacheService();
                }
            }
    	}    	
    	return scriptObjectCacheService;
    	
//        if (scriptObjectCacheService != null) {
//            return scriptObjectCacheService;
//        }
//        synchronized (ScriptObjectCacheService.class) {
//            if (scriptObjectCacheService == null) {
//                scriptObjectCacheService = new ScriptObjectCacheService();
//            }
//        }
//        return scriptObjectCacheService;
    }

    public ScriptObject<CommonScript> getScriptObject(String key) {
        if (simpleRamCache.hasKey(key)) {
            return simpleRamCache.get(key);
        }
        return null;
    }

    public void cacheScriptObject(String key, ScriptObject<CommonScript> scriptObject) {
        simpleRamCache.put(key, scriptObject);
    }

    public void remove(String key){
        simpleRamCache.del(key);
    }
}
