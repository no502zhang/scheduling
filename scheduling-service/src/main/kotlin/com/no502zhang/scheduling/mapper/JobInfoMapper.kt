package com.no502zhang.scheduling.mapper

import com.no502zhang.scheduling.model.JobInfo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface JobInfoMapper {
    fun insertJobInfo(jobInfo: JobInfo)

    fun updateJobInfo(jobInfo: JobInfo): Int

    fun deleteJobInfo(@Param("id") id: Int): Int

    fun getJobInfo(@Param("id") id: Int): JobInfo

    fun listJobInfo(jobInfo: JobInfo): List<JobInfo>
}