package com.no502zhang.scheduling.service.impl

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component


@Configuration
@Component
@EnableScheduling
open class ScheduleTask {
    fun test() {
        println("test")
    }
}