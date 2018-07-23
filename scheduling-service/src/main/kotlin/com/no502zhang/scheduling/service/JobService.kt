package com.no502zhang.scheduling.service

import com.no502zhang.scheduling.model.Job

interface JobService {
    fun createJob(job: Job);
}