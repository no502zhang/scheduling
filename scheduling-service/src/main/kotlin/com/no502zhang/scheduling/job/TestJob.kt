package com.no502zhang.scheduling.job

import com.no502zhang.scheduling.model.JobInfo
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class TestJob : Job {
    override fun execute(context: JobExecutionContext) {
        println("测试任务[${(context.jobDetail.jobDataMap.get("jobInfo") as JobInfo).name}] is running…………………………………… ")
    }
}