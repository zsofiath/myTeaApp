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

    var currentNumber = "6"
    var number = 6
    var currentTea: Tea? = null
    var currentTea_AmountOfLeaf = ""
    var currentTea_BrewingTemperature = ""
    var currentTea_type = ""
    var infusions: MutableLiveData<List<Int>> = MutableLiveData()


    var i = 0

    private var timer: CountDownTimer? = null

    private val _countDownValue = MutableLiveData<String>()
    val countDownValue: LiveData<String>
        get() = _countDownValue

    private val _clickable = MutableLiveData<Boolean>()
    val clickable: LiveData<Boolean>
        get() = _clickable

    init {

        infusions.value =  Tea.currentTea?.brewingTimes!!.map { i -> i.value }

        currentTea = Tea.currentTea
        currentNumber = "Start brewing tea"
        number = currentTea!!.brewingTimes[i].value

        currentTea_AmountOfLeaf = Tea.currentTea?.amount.toString()+"g/100ml"
        currentTea_BrewingTemperature = Tea.currentTea?.temp.toString()+"C°"
        currentTea_type = Tea.currentTea?.type.toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaViewModel", "TeaViewModel destroyed")
    }


    private fun decreaseValue(countDownValue: MutableLiveData<String>){
        countDownValue.value = (countDownValue.value)?.toInt()?.minus(1).toString()
        currentNumber = countDownValue.value.toString()
    }

    public fun startCountDown(){

        _countDownValue.value = number.toString()
        currentNumber = number.toString()
        _clickable.value = false

        Log.i("________________",(1).toString()+" ----- "+infusions.value!!.count().toString())
        infusions.value = infusions.value!!.toList().subList(1, infusions.value!!.count())

        timer = object : CountDownTimer((number*1000).toLong(),1000) {
            override fun onTick(p0: Long) {
                decreaseValue(_countDownValue)
            }

            override fun onFinish() {

                if(i < currentTea!!.brewingTimes.count()-1) {
                    i++
                    currentNumber = currentTea!!.brewingTimes[i].visibleValue
                    number = currentTea!!.brewingTimes[i].value

                    _countDownValue.value = "Start next infusion"

                    _clickable.value = true
                }
                else {
                    _clickable.value = false
                    _countDownValue.value = "Add new leaves!"
                }

            }
        }
        timer?.start()
    }

}