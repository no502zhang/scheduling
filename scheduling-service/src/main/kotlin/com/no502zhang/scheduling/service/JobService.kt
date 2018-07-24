package com.no502zhang.scheduling.service

import com.github.pagehelper.Page
import com.github.pagehelper.PageInfo
import com.no502zhang.scheduling.model.JobInfo

interface JobService {
    fun createJob(jobInfo: JobInfo): JobInfo

    fun updateJob(id: Int, jobInfo: JobInfo): JobInfo

    fun deleteJob(id: Int): Boolean

    fun getJob(id: Int): JobInfo

    fun listJobs(jobInfo: JobInfo, pageNum: Int = 1, pageSize: Int = 10): PageInfo<JobInfo>
}