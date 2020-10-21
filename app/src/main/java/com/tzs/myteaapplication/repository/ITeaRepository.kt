package com.tzs.myteaapplication.repository

import androidx.lifecycle.LiveData
import com.tzs.myteaapplication.models.Tea

interface ITeaRepository {

    fun insert(tea: Tea)
    fun update(tea: Tea)
    fun getTea(id: Int) : Tea
    fun getAllTea(): LiveData<List<Tea>>
    fun delete(tea: Tea)

}