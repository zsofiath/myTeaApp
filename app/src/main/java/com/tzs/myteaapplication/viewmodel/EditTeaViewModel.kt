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
    var currentTea_Name = ""
    var currentTea_AmountOfLeaf = ""
    var currentTea_BrewingTemperature = ""
    init {

    }

    public fun saveTea(): Boolean {
        if(allDataFilled()) {
            viewModelScope.launch {
                val newTea = com.tzs.myteaapplication.database.Tea()
                newTea.name = currentTea_Name
                newTea.temperature = currentTea_BrewingTemperature.toInt()
                newTea.amount = currentTea_AmountOfLeaf.toInt()

                insert(newTea)
            }
            return true;
        }
        return false;
    }

    private fun allDataFilled(): Boolean {
        return currentTea_Name != "" && currentTea_BrewingTemperature!= "" && currentTea_AmountOfLeaf != ""
    }

    private suspend fun insert(night: com.tzs.myteaapplication.database.Tea) {
        database.insert(night)
    }
}