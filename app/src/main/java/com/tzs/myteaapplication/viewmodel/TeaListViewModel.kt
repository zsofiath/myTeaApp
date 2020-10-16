package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.database.Tea
import com.tzs.myteaapplication.database.TeaDatabaseDao
import kotlinx.coroutines.launch

class TeaListViewModel(
    val database: TeaDatabaseDao,
    application: Application
): ViewModel() {


    val teas_liveData: LiveData<List<Tea>> = database.getAllTea()



    init {

    }



    override fun onCleared() {
        super.onCleared()
        Log.i("TeaListViewModel", "TeaListViewModel destroyed")
    }
}