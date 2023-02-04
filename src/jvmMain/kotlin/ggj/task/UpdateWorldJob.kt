package ggj.task

import java.time.ZonedDateTime

class UpdateWorldJob {

    fun updateWorld() {
        println("the word is updating (at ${ZonedDateTime.now()})")
        Thread.sleep(1000L)
    }
}