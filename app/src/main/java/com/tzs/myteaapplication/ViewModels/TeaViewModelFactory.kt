package com.tzs.myteaapplication.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


    class TeaViewModelFactory(private val teaId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TeaViewModel::class.java)) {
                return TeaViewModel(teaId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
