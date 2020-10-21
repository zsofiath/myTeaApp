package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.repository.ITeaRepository
import com.tzs.myteaapplication.repository.TeaRepository
import kotlinx.coroutines.launch
import java.util.stream.Collectors.toList

class EditTeaViewModel(
    val repository: ITeaRepository,
    application: Application
): ViewModel() {
    var currentTea_Name = ""
    var currentTea_AmountOfLeaf = ""
    var currentTea_BrewingTemperature = ""
    lateinit var teaTypes:List<TeaTypes>

    init {
        teaTypes = enumValues<TeaTypes>().toList()
    }

    public fun saveTea(): Boolean {
        if(allDataFilled()) {
            prepareAndSave()
            return true;
        }
        return false;
    }

    private fun prepareAndSave() {
        viewModelScope.launch {
            val newTea = Tea(0)
            newTea.name = currentTea_Name
            newTea.temp = currentTea_BrewingTemperature.toInt()
            newTea.amount = currentTea_AmountOfLeaf.toInt()

            repository.insert(newTea)
        }
    }

    private fun allDataFilled(): Boolean {
        return currentTea_Name != "" && currentTea_BrewingTemperature!= "" && currentTea_AmountOfLeaf != ""
    }

    private suspend fun insert(night: Tea) {
       // database.insert(night)
    }
}