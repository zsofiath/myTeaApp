package com.tzs.myteaapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.repository.ITeaRepository


class FakeTeaRepository(var teas: MutableList<Tea>): ITeaRepository {

    override suspend fun insert(tea: Tea) {
       teas.add(tea)
    }

    override suspend fun update(tea: Tea) {
        /*var teaItem = teas.find { t: Tea ->  t.id == tea.id}
        teaItem?.name = tea.name
        teaItem?.temp = tea.temp
        teaItem?.type = tea.type
        teaItem?.amount = tea.amount*/

        teas.set(tea.id, tea)
    }

    override suspend fun getTea(id: Int): Tea {
        return teas.find { t: Tea ->  t.id == id}!!
    }

    override fun getAllTea(): LiveData<List<Tea>> {
        return  MutableLiveData<List<Tea>>(teas)
    }

    override suspend fun delete(tea: Tea) {
        var filtered = teas.find { it.id == tea.id }
        teas.remove(filtered)
    }
}