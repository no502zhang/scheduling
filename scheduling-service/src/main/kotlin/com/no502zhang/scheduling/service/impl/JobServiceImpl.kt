package com.no502zhang.scheduling.service.impl

import com.no502zhang.scheduling.job.TestJob
import com.no502zhang.scheduling.model.Job
import com.no502zhang.scheduling.service.JobService
import org.quartz.*
import org.springframework.stereotype.Service

@Service
class JobServiceImpl(val scheduler: Scheduler) : JobService {
    override fun createJob(job: Job) {
        scheduleJob(job)
    }

    private fun scheduleJob(job: Job) {
        //配置定时任务对应的Job，这里执行的是ScheduledJob2类中定时的方法
        val jobDetail = JobBuilder.newJob(TestJob::class.java).withIdentity(job.name, "defaultGroup").build()
        val scheduleBuilder = CronScheduleBuilder.cronSchedule(job.cron)        // 每5s执行一次
        val cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger", "defaultGroup").withSchedule<CronTrigger>(scheduleBuilder).build()
        scheduler.scheduleJob(jobDetail, cronTrigger)
    }
}