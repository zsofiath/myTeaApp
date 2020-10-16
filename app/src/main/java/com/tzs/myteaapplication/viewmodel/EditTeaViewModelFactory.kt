package com.tzs.myteaapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tzs.myteaapplication.database.TeaDatabaseDao

class EditTeaViewModelFactory(private val database: TeaDatabaseDao,
                              private val application: Application
) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?>  create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTeaViewModel::class.java)) {
            return EditTeaViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}