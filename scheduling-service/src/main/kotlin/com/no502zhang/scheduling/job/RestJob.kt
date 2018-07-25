package com.no502zhang.scheduling.job

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

import org.springframework.beans.factory.annotation.Autowired

@Component
@EnableScheduling
class RestJob : Job {
    @Autowired
    lateinit var restTemplate: RestTemplate

    override fun execute(context: JobExecutionContext) {
        println("开始任务[${context.jobDetail.jobDataMap["jobInfo.name"]}]")

        var headers = HttpHeaders()
        headers.contentType = MediaType.valueOf("application/json;UTF-8")
        val paramStr = "{\"id\":0, \"name\":\"=== test name ===\", \"cron\":\"0/5 * * * * ?\"}"
        val strEntity = HttpEntity<String>(paramStr, headers)

        val response = restTemplate.postForEntity("http://localhost:8300/scheduling/jobs/test", strEntity, String::class.java)
        val body = response.getBody()

        println(body)
    }
}