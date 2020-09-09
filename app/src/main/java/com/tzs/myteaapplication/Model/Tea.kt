package com.tzs.myteaapplication.Model

class Tea(val id:Int) {
    var name: String? = null
    var temp: Int? = null
    var amount: Int? = null
    var type: TeaTypes? = null
    var brewingTimes = IntArray(5) { 0 }


}