package com.no502zhang.scheduling.controller

import com.github.pagehelper.PageInfo
import com.no502zhang.scheduling.model.JobInfo
import com.no502zhang.scheduling.service.JobService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/jobs")
class JobController(private val jobService: JobService) {

    @PostMapping("/test")
    fun createTest(): ResponseEntity<String> {
        return ResponseEntity.ok().body("OK")
    }

    @PostMapping("/")
    fun create(@RequestBody jobInfo: JobInfo): ResponseEntity<JobInfo> {
        jobService.createJob(jobInfo)
        return ResponseEntity.ok().body(jobInfo)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Boolean> {
        jobService.deleteJob(id)
        return ResponseEntity.ok().body(true)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int,
               @RequestBody jobInfo: JobInfo): ResponseEntity<Boolean> {
        val flag = false
        return ResponseEntity.ok().body(flag)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): ResponseEntity<JobInfo> {
        val job = JobInfo(id, "mock")
        return ResponseEntity.ok().body(job)
    }

    @GetMapping("/")
    fun list(jobInfo: JobInfo, @RequestParam(value = "pageNum", required = false) pageNum: Int = 1, @RequestParam(value = "pageSize", required = false) pageSize: Int = 10): ResponseEntity<PageInfo<JobInfo>> {
        val result = jobService.listJobs(jobInfo, pageNum, pageSize)
        return ResponseEntity.ok().body<PageInfo<JobInfo>>(result)
    }
}