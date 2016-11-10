package com.qbin.tuitui.common.schedule.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;

/**
 * 描述：定时任务
 * author qiaobin   2016/10/18 14:37.
 */
public class QuartsUtil {
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

    /**
      * 功能描述：添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
      * @author qiaobin
      * @date 2016/10/18  14:47
      * @param jobName 任务名
      * @param clazz 任务
      * @param cron 时间设置
      */
    public static void addJob(String jobName, Class clazz, String cron) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(clazz)
                    .withIdentity(jobName, JOB_GROUP_NAME)
                    .build();
            //触发器
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：添加一个定时任务
     * @author qiaobin
     * @date 2016/10/18  14:47
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param clazz 任务
     * @param cron 时间设置，参考quartz说明文档
     */
    @SuppressWarnings("unchecked")
    public static void addJob(String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName, Class clazz,
                              String cron) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(clazz)
                    .withIdentity(jobName, jobGroupName)
                    .build();
            //触发器
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            sched.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * @author qiaobin
     * @date 2016/10/18  14:47
     * @param jobName
     * @param time
     */
    @SuppressWarnings("unchecked")
    public static void modifyJobTime(String jobName, String time) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(jobName, JOB_GROUP_NAME));
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, JOB_GROUP_NAME));
                Class objJobClass = jobDetail.getJobClass();
                removeJob(jobName);
                addJob(jobName, objJobClass, time);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：修改一个任务的触发时间
     * @author qiaobin
     * @date 2016/10/18  14:47
     * @param triggerName
     * @param triggerGroupName
     * @param cron
     */
    public static void modifyJobTime(String triggerName,
                                     String triggerGroupName, String cron) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(triggerName,triggerGroupName));
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                CronTrigger ct = (CronTrigger) trigger;
                // 修改时间
                ct.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 重启触发器
                sched.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     * @author qiaobin
     * @date 2016/10/18  14:47
     * @param jobName
     */
    public static void removeJob(String jobName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));// 停止触发器
            sched.unscheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, TRIGGER_GROUP_NAME));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述： 移除一个任务
     * @author qiaobin
     * @date 2016/10/18  14:47
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void removeJob(String jobName, String jobGroupName,
                                 String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));// 停止触发器
            sched.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
