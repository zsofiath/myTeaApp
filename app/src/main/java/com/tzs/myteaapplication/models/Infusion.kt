package com.tzs.myteaapplication.models

class Infusion {
    var value: Int = 0

    var visibleValue: String
        get() = value.toString()
        set(inValue) {
            value = inValue.toInt()
        }
}