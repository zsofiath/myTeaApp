package com.tzs.myteaapplication.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel

class TeaViewModel: ViewModel() {
    init {
        Log.i("TeaViewModel", "TeaViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaViewModel", "TeaViewModel destroyed")
    }
}