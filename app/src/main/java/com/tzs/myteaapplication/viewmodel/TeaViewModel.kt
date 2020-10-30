package com.tzs.myteaapplication.viewmodel

import android.content.Context
import android.os.CountDownTimer
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tzs.myteaapplication.models.Infusion
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer
import kotlin.math.log

class TeaViewModel(teaId: Int): ViewModel() {

    var currentTea: Tea? = null
    var currentTea_Name = ""
    var currentTea_AmountOfLeaf = ""
    var currentTea_BrewingTemperature = ""
    var currentTea_type = ""
    var currentTea_infusions: List<Int> = listOf()
    var infusions: MutableLiveData<List<Int>> = MutableLiveData()

    private var timer: CountDownTimer? = null

    private val _countDownValue = MutableLiveData<Int>()
    val countDownValue: LiveData<Int>
        get() = _countDownValue

    init {

        currentTea_infusions =  Tea.currentTea?.brewingTimes!!.map { i -> i.value }
        infusions.value = currentTea_infusions

        currentTea = Tea.currentTea

        currentTea_AmountOfLeaf = Tea.currentTea?.amount.toString()+"g/100ml"
        currentTea_BrewingTemperature = Tea.currentTea?.temp.toString()+"C°"
        currentTea_type = Tea.currentTea?.type.toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaViewModel", "TeaViewModel destroyed")
    }


    private fun decreaseValue(countDownValue: MutableLiveData<Int>){
        countDownValue.value = (countDownValue.value)?.minus(1)
    }

    public fun startCountDown(number: Int){
        _countDownValue.value = number


        timer = object : CountDownTimer((number*1000).toLong(),1000) {
            override fun onTick(p0: Long) {
                decreaseValue(_countDownValue)
            }

            override fun onFinish() {

            }
        }
        timer?.start()
    }

}