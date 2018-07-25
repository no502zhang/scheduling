package com.no502zhang.scheduling.job

import org.springframework.scheduling.quartz.AdaptableJobFactory
import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.config.AutowireCapableBeanFactory

class SpringJobFactory(private val capableBeanFactory: AutowireCapableBeanFactory) : AdaptableJobFactory() {

    override fun createJobInstance(bundle: TriggerFiredBundle): Any {
        //调用父类的方法
        val jobInstance = super.createJobInstance(bundle)
        //进行注入
        capableBeanFactory.autowireBean(jobInstance)
        return jobInstance
    }
}