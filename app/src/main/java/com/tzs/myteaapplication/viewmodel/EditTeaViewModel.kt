package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.repository.ITeaRepository
import com.tzs.myteaapplication.repository.TeaRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.stream.Collectors.toList

class EditTeaViewModel(
    val repository: ITeaRepository,
    application: Application
): ViewModel() {
    var currentTea_Name = ""
    var currentTea_AmountOfLeaf = ""
    var currentTea_BrewingTemperature = ""
    var currentTea_type: TeaTypes? = null

    init {
        if(Tea.currentTea != null) {
            currentTea_Name = Tea.currentTea?.name!!
            currentTea_AmountOfLeaf = Tea.currentTea?.amount!!.toString()
            currentTea_BrewingTemperature = Tea.currentTea?.temp!!.toString()
            currentTea_type = Tea.currentTea?.type
        }
    }

    public fun saveTea(): Boolean {
        if(allDataFilled()) {
            if(Tea.currentTea != null) {
                prepareAndUpdate(Tea.currentTea?.id!!)
            } else {
                prepareAndSave()
            }

            return true;
        }
        return false;
    }

    public fun getCheckedViewId(): Int {
        when (currentTea_type) {
            TeaTypes.WHITE -> return R.id.WHITE
            TeaTypes.BLACK -> return R.id.BLACK
            TeaTypes.HERBAL -> return R.id.HERBAL
            TeaTypes.PUERH -> return R.id.PUERH
            TeaTypes.GREEN -> return R.id.GREEN
            TeaTypes.OOLONG -> return R.id.OOLONG
            TeaTypes.YELLOW -> return R.id.YELLOW
            else -> {
                throw Exception("Tea Type not found.")
            }
        }
    }

    public fun onTypeSelected(checkedId: Int) {
        when (checkedId) {
            R.id.WHITE -> currentTea_type = TeaTypes.WHITE
            R.id.BLACK -> currentTea_type = TeaTypes.BLACK
            R.id.HERBAL -> currentTea_type = TeaTypes.HERBAL
            R.id.PUERH -> currentTea_type = TeaTypes.PUERH
            R.id.GREEN -> currentTea_type = TeaTypes.GREEN
            R.id.OOLONG -> currentTea_type = TeaTypes.OOLONG
            R.id.YELLOW -> currentTea_type = TeaTypes.YELLOW
            else -> {
                throw Exception("Tea Type not found.")
            }
        }
    }

    private fun prepareAndSave() {
        viewModelScope.launch {
            val newTea = Tea(0)
            newTea.name = currentTea_Name
            newTea.temp = currentTea_BrewingTemperature.toInt()
            newTea.amount = currentTea_AmountOfLeaf.toInt()
            newTea.type = currentTea_type

            repository.insert(newTea)
        }
    }

    private fun prepareAndUpdate(id: Int) {
        viewModelScope.launch {
            val newTea = Tea(id)
            newTea.name = currentTea_Name
            newTea.temp = currentTea_BrewingTemperature.toInt()
            newTea.amount = currentTea_AmountOfLeaf.toInt()
            newTea.type = currentTea_type

            repository.update(newTea)
        }
    }

    private fun allDataFilled(): Boolean {
        return currentTea_Name != "" && currentTea_BrewingTemperature!= "" && currentTea_AmountOfLeaf != ""
    }

    private suspend fun insert(night: Tea) {
       // database.insert(night)
    }
}