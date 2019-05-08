package com.alipay.mcenter.core.engine.aengine.eval.quartz.service;

import com.alipay.mcenter.core.engine.aengine.eval.quartz.model.ScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by zihong.cll on 2017/6/16.
 */
public interface JobTaskService {

    void addJob(ScheduleJob job) throws SchedulerException;

    List<ScheduleJob> getRunningJob() throws SchedulerException;

    void pauseJob(ScheduleJob scheduleJob) throws SchedulerException ;

    void resumeJob(ScheduleJob scheduleJob) throws SchedulerException;

    void deleteJob(ScheduleJob scheduleJob) throws SchedulerException;

    void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;

    void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;
}
