package com.tzs.myteaapplication.repository

import androidx.lifecycle.LiveData
import com.tzs.myteaapplication.models.Tea

interface ITeaRepository {

    suspend fun insert(tea: Tea)
    suspend fun update(tea: Tea)
    suspend fun getTea(id: Int) : Tea
    fun getAllTea(): LiveData<List<Tea>>
    suspend fun delete(tea: Tea)

}