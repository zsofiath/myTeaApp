package com.tzs.myteaapplication.models

import kotlinx.coroutines.delay
import kotlin.concurrent.thread


class Infusion {
    var value: Int = 0

    var visibleValue: String
        get() = value.toString()
        set(inValue) {
            value = inValue.toInt()
        }

    fun countDown(){
        thread {
            while (value > 0) {
                Thread.sleep(1000)
                value--
            }

        }
    }
}