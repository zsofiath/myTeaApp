package com.tzs.myteaapplication.ViewModels

import android.content.Context
import android.os.CountDownTimer
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tzs.myteaapplication.Model.Tea
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer
import kotlin.math.log

class TeaViewModel(teaId: Int): ViewModel() {

    var currentTea: Tea? = null


    private var timer: CountDownTimer? = null

    private val _countDownValue = MutableLiveData<Int>()
    val countDownValue: LiveData<Int>
        get() = _countDownValue

    init {
        Log.i("TeaViewModel", "TeaViewModel created. ID: $teaId")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaViewModel", "TeaViewModel destroyed")
    }

    fun fetchTea(id: Int): Tea {
        var tea = Tea(id)
        tea.name = "King of Puerh"
        return tea
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