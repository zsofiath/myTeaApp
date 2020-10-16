package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.Model.Tea
import com.tzs.myteaapplication.database.TeaDatabaseDao
import kotlinx.coroutines.launch

class EditTeaViewModel(
    val database: TeaDatabaseDao,
    application: Application
): ViewModel() {
    var currentTea: Tea = Tea(0)
    init {

    }

    public fun saveTea() {
        viewModelScope.launch {
            val newNight = com.tzs.myteaapplication.database.Tea()
            newNight.name = ""+currentTea.name

            insert(newNight)
        }
    }

    private suspend fun insert(night: com.tzs.myteaapplication.database.Tea) {
        database.insert(night)
    }
}