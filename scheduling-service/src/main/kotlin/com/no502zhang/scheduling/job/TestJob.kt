package com.no502zhang.scheduling.job

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class TestJob : Job {
    override fun execute(context: JobExecutionContext) {
        println("这是一个测试任务 is running…………………………………… ")
    }
}