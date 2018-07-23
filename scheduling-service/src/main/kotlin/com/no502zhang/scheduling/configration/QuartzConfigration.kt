package com.no502zhang.scheduling.configration

import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.context.annotation.Configuration

@Configuration
open class QuartzConfigration {

    @Bean
    open fun SchedulerFactory(): SchedulerFactoryBean {
        return SchedulerFactoryBean()
    }
}