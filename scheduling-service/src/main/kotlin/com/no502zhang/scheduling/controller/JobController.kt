package com.no502zhang.scheduling.controller

import com.no502zhang.scheduling.model.Job
import com.no502zhang.scheduling.service.JobService
import org.quartz.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/jobs")
class JobController(val jobService: JobService) {

    @PostMapping("/")
    fun create(@RequestBody job: Job): ResponseEntity<Job> {
        jobService.createJob(job)
        return ResponseEntity.ok().body(job)
    }


}