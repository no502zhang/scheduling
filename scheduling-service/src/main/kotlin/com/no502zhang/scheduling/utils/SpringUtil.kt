package com.no502zhang.scheduling.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringUtil : ApplicationContextAware {
    private var applicationContext: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext
        }
    }

    //获取applicationContext
    fun getApplicationContext(): ApplicationContext? {
        return this.applicationContext
    }

    //通过name获取 Bean.
    fun getBean(name: String): Any? {
        return this.applicationContext?.getBean(name)

    }

    //通过class获取Bean.
    fun <T> getBean(clazz: Class<T>): T? {
        return this.applicationContext?.getBean(clazz)
    }

    //通过name,以及Clazz返回指定的Bean
    fun <T> getBean(name: String, clazz: Class<T>): T? {
        return this.applicationContext?.getBean(name, clazz)
    }
}