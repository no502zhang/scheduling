package com.no502zhang.scheduling.model

class JobInfo(id: Int = 0, name: String = "") {
    val id: Int = id
    var name: String = name

    var cron: String = ""
}