package com.no502zhang.scheduling.job

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
class RestJob(private var restTemplate: RestTemplate) : Job {
    override fun execute(context: JobExecutionContext) {
        println("开始任务[${context.jobDetail.jobDataMap["jobInfo.name"]}]")
        val response = restTemplate.postForEntity("localhost:8300/scheduling/jobs/test", null, String::class.java)
        val body = response.getBody()

        println(body)
    }
}