package com.no502zhang.scheduling

import com.no502zhang.scheduling.service.impl.ScheduleTask
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.quartz.Trigger;
import org.springframework.context.annotation.Configuration

@Configuration
open class QuartzConfigration {

    @Bean(name = arrayOf("jobDetail"))
    open fun getJobDetail(task: ScheduleTask): MethodInvokingJobDetailFactoryBean {
        println("getJobDetail")
        // ScheduleTask为需要执行的任务
        val jobDetail = MethodInvokingJobDetailFactoryBean()
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会bing执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(true)

        jobDetail.setName("scheduler")// 设置任务的名字
        jobDetail.setGroup("scheduler_group")// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 这两行代码表示执行task对象中的scheduleTest方法。定时执行的逻辑都在scheduleTest。
         */
        jobDetail.setTargetObject(task)
        jobDetail.setTargetMethod("test")

        return jobDetail
    }

    @Bean(name = arrayOf("jobTrigger"))
    open fun getJobTrigger(jobDetail: MethodInvokingJobDetailFactoryBean): CronTriggerFactoryBean {
        println("getJobTrigger")
        val tigger = CronTriggerFactoryBean()
        tigger.setJobDetail(jobDetail.`object`)
        tigger.setCronExpression("0/6 * * * * ?")// 表示每隔6秒钟执行一次
        //tigger.set
        tigger.setName("myTigger")// trigger的name
        return tigger
    }

    @Bean(name = arrayOf("scheduler"))
    open fun getScheduler(cronJobTrigger: Trigger): SchedulerFactoryBean {
        println("getScheduler")
        val bean = SchedulerFactoryBean()
        //设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
        bean.setOverwriteExistingJobs(true)
        // 延时启动，应用启动5秒后  ，定时器才开始启动
        bean.setStartupDelay(5)
        // 注册定时触发器
        bean.setTriggers(cronJobTrigger)
        return bean
    }

    //多任务时的Scheduler，动态设置Trigger。一个SchedulerFactoryBean可能会有多个Trigger
    @Bean(name = arrayOf("multitaskScheduler"))
    open fun getMultitaskScheduler(): SchedulerFactoryBean {
        println("getMultitaskScheduler")
        val schedulerFactoryBean = SchedulerFactoryBean()
        return schedulerFactoryBean
    }
}