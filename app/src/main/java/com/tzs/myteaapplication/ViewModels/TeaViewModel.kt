package com.tzs.myteaapplication.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tzs.myteaapplication.Model.Tea

class TeaViewModel: ViewModel() {

    var currentTea: Tea? = null

    init {
        Log.i("TeaViewModel", "TeaViewModel created")
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
}