package com.no502zhang.scheduling.service.impl

import com.github.pagehelper.Page
import com.github.pagehelper.PageInfo
import com.no502zhang.scheduling.job.TestJob
import com.no502zhang.scheduling.model.JobInfo
import com.no502zhang.scheduling.service.JobService
import org.quartz.*
import org.springframework.stereotype.Service
import java.util.ArrayList
import org.quartz.JobBuilder
import org.quartz.JobDetail


@Service
class JobServiceImpl(private val scheduler: Scheduler) : JobService {
    private val jobsList = ArrayList<JobInfo>()

    private val jobNamePrefix = "job"
    private val defaultJobGroup = "defaultGroup"

    private val triggerNamePrefix = "trigger"
    private val defaultTriggerGroup = "defaultGroup"

    override fun createJob(jobInfo: JobInfo): JobInfo {
        // 保存
        jobsList.add(jobInfo)

        val jobDetail = JobBuilder.newJob(TestJob::class.java).withIdentity("$jobNamePrefix#${jobInfo.id}", "$defaultJobGroup").build()
        // 运行参数
        jobDetail.jobDataMap.put("jobInfo", jobInfo)

        val scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.cron)
        val trigger = TriggerBuilder.newTrigger().withIdentity("$triggerNamePrefix#${jobInfo.id}", "$defaultTriggerGroup").withSchedule<CronTrigger>(scheduleBuilder).build()
        scheduler.scheduleJob(jobDetail, trigger)

        return jobInfo
    }

    override fun updateJob(id: Int, jobInfo: JobInfo): JobInfo {
        // TODO 查询记录
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteJob(id: Int): Boolean {
        // TODO 删除数据库中的记录

        deleteSchedulerJob(id)
        return true
    }

    override fun getJob(id: Int): JobInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listJobs(jobInfo: JobInfo, pageNum: Int?, pageSize: Int?): PageInfo<JobInfo> {
        return PageInfo(jobsList)
    }

    private fun deleteSchedulerJob(id: Int): Boolean {
        val key = JobKey.jobKey("$jobNamePrefix#$id", "$defaultJobGroup")
        scheduler.deleteJob(key)
        return true
    }
}