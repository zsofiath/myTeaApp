package com.tzs.myteaapplication.models

class Tea(val id:Int) {

    var name: String? = null
    var temp: Int? = null
    var amount: Int? = null
    var type: TeaTypes? = null
    var brewingTimes = emptyList<Int>()

    companion object {
        var currentTea: Tea? = null
    }

}