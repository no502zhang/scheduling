package com.no502zhang.scheduling.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.no502zhang.scheduling.job.RestJob
import com.no502zhang.scheduling.model.JobInfo
import com.no502zhang.scheduling.service.JobService
import org.quartz.*
import org.springframework.stereotype.Service
import org.quartz.JobBuilder
import com.no502zhang.scheduling.mapper.JobInfoMapper

@Service
class JobServiceImpl(private val scheduler: Scheduler, private val jobInfoMapper: JobInfoMapper) : JobService {

    private val jobNamePrefix = "job"
    private val defaultJobGroup = "defaultGroup"

    private val triggerNamePrefix = "trigger"
    private val defaultTriggerGroup = "defaultGroup"

    override fun createJob(jobInfo: JobInfo): JobInfo {
        // 保存
        jobInfoMapper.insertJobInfo(jobInfo)

        val jobDetail = JobBuilder.newJob(RestJob::class.java).withIdentity("$jobNamePrefix#${jobInfo.id}", defaultJobGroup).build()
        // 运行参数
        jobDetail.jobDataMap["jobInfo.id"] = jobInfo.id
        jobDetail.jobDataMap["jobInfo.name"] = jobInfo.name

        val scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.cron)
        val trigger = TriggerBuilder.newTrigger().withIdentity("$triggerNamePrefix#${jobInfo.id}", defaultTriggerGroup).withSchedule<CronTrigger>(scheduleBuilder).build()
        scheduler.scheduleJob(jobDetail, trigger)

        return jobInfo
    }

    override fun updateJob(id: Int, jobInfo: JobInfo): JobInfo {
        // TODO 查询记录
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteJob(id: Int): Boolean {
        // 删除数据库中的记录
        jobInfoMapper.deleteJobInfo(id)

        deleteSchedulerJob(id)
        return true
    }

    override fun getJob(id: Int): JobInfo {
        return jobInfoMapper.getJobInfo(id)
    }

    override fun listJobs(jobInfo: JobInfo, pageNum: Int, pageSize: Int): PageInfo<JobInfo> {
        PageHelper.startPage<JobInfo>(pageNum, pageSize)
        val jobsList = jobInfoMapper.listJobInfo(jobInfo)
        return PageInfo(jobsList)
    }

    private fun deleteSchedulerJob(id: Int): Boolean {
        jobInfoMapper.deleteJobInfo(id)

        val key = JobKey.jobKey("$jobNamePrefix#$id", defaultJobGroup)
        scheduler.deleteJob(key)
        return true
    }
}