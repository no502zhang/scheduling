package com.no502zhang.scheduling.configration

import com.no502zhang.scheduling.job.SpringJobFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
open class QuartzConfigration {

    @Value("\${quartz.scheduler.instanceName}")
    private val quartzInstanceName: String? = null

    @Value("\${org.quartz.dataSource.myDS.driver}")
    private val myDSDriver: String? = null

    @Value("\${org.quartz.dataSource.myDS.URL}")
    private val myDSURL: String? = null

    @Value("\${org.quartz.dataSource.myDS.user}")
    private val myDSUser: String? = null

    @Value("\${org.quartz.dataSource.myDS.password}")
    private val myDSPassword: String? = null

    private fun quartzProperties(): Properties {
        val prop = Properties()
        prop["quartz.scheduler.instanceName"] = quartzInstanceName
        prop["org.quartz.scheduler.instanceId"] = "AUTO"
        prop["org.quartz.scheduler.skipUpdateCheck"] = "true"
        prop["org.quartz.scheduler.jmx.export"] = "true"

        prop["org.quartz.jobStore.class"] = "org.quartz.impl.jdbcjobstore.JobStoreTX"
        prop["org.quartz.jobStore.driverDelegateClass"] = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate"
        prop["org.quartz.jobStore.dataSource"] = "quartzDataSource"
        prop["org.quartz.jobStore.tablePrefix"] = "QRTZ_"
        prop["org.quartz.jobStore.isClustered"] = "true"

        prop["org.quartz.jobStore.clusterCheckinInterval"] = "20000"
        prop["org.quartz.jobStore.dataSource"] = "myDS"
        prop["org.quartz.jobStore.maxMisfiresToHandleAtATime"] = "1"
        prop["org.quartz.jobStore.misfireThreshold"] = "120000"
        prop["org.quartz.jobStore.txIsolationLevelSerializable"] = "true"
        prop["org.quartz.jobStore.selectWithLockSQL"] = "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE"

        prop["org.quartz.threadPool.class"] = "org.quartz.simpl.SimpleThreadPool"
        prop["org.quartz.threadPool.threadCount"] = "10"
        prop["org.quartz.threadPool.threadPriority"] = "5"
        prop["org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread"] = "true"

        prop["org.quartz.dataSource.myDS.driver"] = myDSDriver
        prop["org.quartz.dataSource.myDS.URL"] = myDSURL
        prop["org.quartz.dataSource.myDS.user"] = myDSUser
        prop["org.quartz.dataSource.myDS.password"] = myDSPassword

        prop["org.quartz.plugin.triggHistory.class"] = "org.quartz.plugins.history.LoggingJobHistoryPlugin"
        prop["org.quartz.plugin.shutdownhook.class"] = "org.quartz.plugins.management.ShutdownHookPlugin"
        prop["org.quartz.plugin.shutdownhook.cleanShutdown"] = "true"
        return prop
    }

    @Bean
    open fun jobFactory(capableBeanFactory: AutowireCapableBeanFactory): SpringJobFactory {
        return SpringJobFactory(capableBeanFactory)
    }

    @Bean
    open fun schedulerFactory(springJobFactory: SpringJobFactory): SchedulerFactoryBean {
        val factory = SchedulerFactoryBean()
        factory.setJobFactory(springJobFactory)

        factory.setOverwriteExistingJobs(true)
        //用于quartz集群,加载quartz数据源
        factory.setStartupDelay(10)
        factory.setQuartzProperties(quartzProperties())
        factory.isAutoStartup = true
        factory.setApplicationContextSchedulerContextKey("applicationContext")
        return factory
    }
}