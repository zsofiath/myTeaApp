package com.tzs.myteaapplication.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tzs.myteaapplication.Model.Tea
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class TeaViewModel: ViewModel() {

    var currentTea: Tea? = null
    val countDownValue = MutableLiveData<Int>()

    init {
        Log.i("TeaViewModel", "TeaViewModel created")
        setCountDown()
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

    private fun setCountDown(){
        countDownValue.value = 10
    }

    private fun decreaseValue(countDownValue: MutableLiveData<Int>){
        countDownValue.value = (countDownValue.value)?.minus(1)
    }

    public fun startCountDown(){
        Log.i("TeaViewModel", "Start count down")
        decreaseValue(countDownValue)
        //doCountDown(countDownValue)
    }

    private fun doCountDown(countDownValue: MutableLiveData<Int>){
        Log.i("TeaViewModel", "Start count down")
        Timer("SettingUp", false).schedule(1000) {
            Log.i("TeaViewModel", "4444")
            val is0 = (countDownValue.value)?.equals(0)
            if(is0 != null && !is0) {
                decreaseValue(countDownValue)
                doCountDown(countDownValue)
            }
        }
    }
}