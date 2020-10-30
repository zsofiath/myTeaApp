package com.tzs.myteaapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.database.TeaDatabaseDao
import com.tzs.myteaapplication.database.TeaEntity
import com.tzs.myteaapplication.models.Infusion
import com.tzs.myteaapplication.models.TeaTypes


class TeaRepository(val database: TeaDatabaseDao): ITeaRepository {

    override suspend fun insert(tea: Tea){
        database.insert(createTeaEntity(tea))
    }

    override suspend fun update(tea: Tea) {
        database.update(createTeaEntity(tea))
    }

    override suspend fun getTea(id: Int): Tea {
        return createTea(database.get(id))
    }

    override fun getAllTea(): LiveData<List<Tea>> {
        val databaseTeaList = database.getAllTea()

        val teaEntities: LiveData<List<TeaEntity>> = database.getAllTea()
        val teas: LiveData<List<Tea>> = Transformations.map(teaEntities) {
                list -> createTeaList(list)
        }

        return teas
    }

    override suspend fun delete(tea: Tea) {
        database.deleteTea(createTeaEntity(tea))
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
        tea.type =  TeaTypes.valueOf(teaEntity.type)
        tea.brewingTimes = convertStringToList(teaEntity.infusions)
        return tea
    }

    private fun createTeaEntity(tea: Tea): TeaEntity {
        var teaEntity = TeaEntity()
        teaEntity.teaId = tea.id
        teaEntity.name = ""+tea.name
        teaEntity.amount = tea.amount!!
        teaEntity.temperature = tea.temp!!
        teaEntity.type = tea.type.toString()
        teaEntity.infusions = convertListToString(tea.brewingTimes)
        return teaEntity
    }

    fun convertStringToList(str: String): List<Infusion>{
        return str.split(',').map { s -> Infusion(s.toInt()) }
    }

    fun convertListToString(list: List<Infusion>): String{
        return list.map { i -> i.value.toString() }.joinToString(",")
    }
}