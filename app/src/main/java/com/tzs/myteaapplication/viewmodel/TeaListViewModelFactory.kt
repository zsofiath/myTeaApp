package com.tzs.myteaapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.repository.TeaRepository

class TeaListViewModelFactory(private val repository: TeaRepository,
                              private val application: Application) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?>  create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeaListViewModel::class.java)) {
            return TeaListViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}