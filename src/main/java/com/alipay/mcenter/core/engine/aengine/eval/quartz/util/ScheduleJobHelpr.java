package com.alipay.mcenter.core.engine.aengine.eval.quartz.util;

import com.alipay.mcenter.core.engine.aengine.eval.quartz.model.ScheduleJob;

/**
 * Created by zihong.cll on 2017/6/19.
 */
public class ScheduleJobHelpr {

    public static String getRouteKeyByJob(ScheduleJob scheduleJob){
        if(scheduleJob != null){
            return scheduleJob.getJobName();
        }
        return null;
    }
}
