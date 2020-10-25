package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.repository.ITeaRepository
import com.tzs.myteaapplication.repository.TeaRepository
import kotlinx.coroutines.launch

class TeaListViewModel(
    val repository: ITeaRepository,
    val application: Application
): ViewModel() {


    val teas_liveData: LiveData<List<Tea>> = repository.getAllTea()



    init {

    }


    fun fetchTea(teaId: Int): LiveData<Tea> {
        var teaLive: MutableLiveData<Tea> = MutableLiveData()
        viewModelScope.launch {
            teaLive.value = getTea(teaId)
        }
        return  teaLive
    }

    suspend fun getTea(teaId: Int):Tea {
        return repository.getTea(teaId)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaListViewModel", "TeaListViewModel destroyed")
    }
}