package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.database.Tea
import com.tzs.myteaapplication.database.TeaDatabaseDao
import kotlinx.coroutines.launch

class TeaListViewModel(
    val database: TeaDatabaseDao,
    application: Application
): ViewModel() {


    val teas: MutableList<Tea> = mutableListOf()
    val teas_liveData: MutableLiveData<List<Tea>> = MutableLiveData<List<Tea>>()

    val nightsString = ".";
    init {
        Log.i("TeaListViewModel", "TeaListViewModel created")
        DebugDB.getAddressLog();
        get()
    }

    private fun get() {

        viewModelScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            val newNight = Tea()
            newNight.name = "Bai mu dan"


            var t = database.getAllTea()

            teas_liveData.value = t;
            teas.clear()
            t.forEach {
               teas.add(it)
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaListViewModel", "TeaListViewModel destroyed")
    }
}