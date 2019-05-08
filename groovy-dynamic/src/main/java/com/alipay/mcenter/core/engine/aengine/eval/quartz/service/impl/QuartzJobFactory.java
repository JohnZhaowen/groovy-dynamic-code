package com.alipay.mcenter.core.engine.aengine.eval.quartz.service.impl;

import com.alipay.mcenter.core.engine.aengine.eval.quartz.model.ScheduleJob;
import com.alipay.mcenter.core.engine.aengine.eval.quartz.util.ScheduleJobHelpr;
import com.alipay.mcenter.core.engine.aengine.service.RouteActionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by zihong.cll on 2017/6/19.
 */
public class QuartzJobFactory implements Job {

    private RouteActionService routeActionService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        routeActionService.callRoute(ScheduleJobHelpr.getRouteKeyByJob(scheduleJob),null);
    }

    public void setRouteActionService(RouteActionService routeActionService) {
        this.routeActionService = routeActionService;
    }
}