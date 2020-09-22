package com.tzs.myteaapplication.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel

class TeaListViewModel: ViewModel() {
    init {
        Log.i("TeaListViewModel", "TeaListViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaListViewModel", "TeaListViewModel destroyed")
    }
}