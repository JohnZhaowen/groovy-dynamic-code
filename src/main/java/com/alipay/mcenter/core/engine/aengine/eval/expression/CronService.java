/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.eval.expression;

import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.support.CronSequenceGenerator;

import com.alipay.mcenter.core.engine.aengine.cache.ram.SimpleRamCache;

/**
 * cron表达式处理服务
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class CronService {

    private static CronService                            cronService;

    private SimpleRamCache<String, CronSequenceGenerator> simpleRamCache = new SimpleRamCache<String, CronSequenceGenerator>(
                                                                             100000);

    private CronService() {
    }

    public static CronService getInstance() {
        if (cronService != null) {
            return cronService;
        }
        synchronized (CronService.class) {
            if (cronService == null) {
                cronService = new CronService();
            }
        }

        return cronService;
    }

    /**
     * 当前时间是否在cron中有效
     * @param cron
     */
    public boolean isNow4agCron(String cron) {

        if (StringUtils.isNotBlank(cron)) {
            cron = "* " + cron;
            if (!simpleRamCache.hasKey(cron)) {
                try {
                    CronSequenceGenerator g = new CronSequenceGenerator(cron, TimeZone.getDefault());
                    simpleRamCache.put(cron, g);
                }catch (Exception e){
                    return false;
                }
            }
            Date now = new Date();
            Date next = simpleRamCache.get(cron).next(now);
            Long diff = next.getTime() - now.getTime();
            if (diff / 1000 <= 1) {
                return true;
            }

        }
        return false;
    }

}
