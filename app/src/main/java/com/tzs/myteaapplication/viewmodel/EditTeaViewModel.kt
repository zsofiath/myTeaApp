package com.tzs.myteaapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.tzs.myteaapplication.database.TeaDatabaseDao

class EditTeaViewModel(
    val database: TeaDatabaseDao,
    application: Application
): ViewModel() {
}