package com.tzs.myteaapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.repository.ITeaRepository
import com.tzs.myteaapplication.repository.TeaRepository

class EditTeaViewModelFactory(private val repository: ITeaRepository,
                              private val application: Application
) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?>  create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTeaViewModel::class.java)) {
            return EditTeaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}