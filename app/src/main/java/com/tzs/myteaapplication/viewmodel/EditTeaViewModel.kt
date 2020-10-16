package com.tzs.myteaapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.amitshekhar.DebugDB
import com.tzs.myteaapplication.Model.Tea
import com.tzs.myteaapplication.database.TeaDatabaseDao

class EditTeaViewModel(
    val database: TeaDatabaseDao,
    application: Application
): ViewModel() {
    var currentTea: Tea = Tea(0)
    init {

    }
}