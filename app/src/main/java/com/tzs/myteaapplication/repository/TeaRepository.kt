package com.tzs.myteaapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.database.TeaEntity

class TeaRepository(val database: TeaDatabaseDao): ITeaRepository {
    override fun insert(tea: Tea) {
        TODO("Not yet implemented")
    }

    override fun update(tea: Tea) {
        TODO("Not yet implemented")
    }

    override fun getTea(id: Int): Tea {
        TODO("Not yet implemented")
    }

    override fun getAllTea(): LiveData<List<Tea>> {
        val databaseTeaList = database.getAllTea()

        val teaEntities: LiveData<List<TeaEntity>> = database.getAllTea()
        val teas: LiveData<List<Tea>> = Transformations.map(teaEntities) {
                list -> createTeaList(list)
        }

        return teas
    }

    override fun delete(tea: Tea) {
        TODO("Not yet implemented")
    }


    private fun createTeaList(list: List<TeaEntity>): List<Tea>{
        return list.map {
            teaEntity ->  createTea(teaEntity)
        }

    }

    private fun createTea(teaEntity: TeaEntity): Tea{
        var tea = Tea(teaEntity.teaId)
        tea.name = teaEntity.name
        tea.amount = teaEntity.amount
        tea.temp = teaEntity.temperature
        return tea
    }
}