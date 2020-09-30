package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.database.TeaDatabaseDao

class TeaListViewModel(
    val database: TeaDatabaseDao,
    application: Application
): ViewModel() {
    init {
        Log.i("TeaListViewModel", "TeaListViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaListViewModel", "TeaListViewModel destroyed")
    }
}