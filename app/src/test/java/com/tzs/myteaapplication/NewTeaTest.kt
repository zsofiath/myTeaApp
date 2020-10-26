package com.tzs.myteaapplication

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import com.tzs.myteaapplication.viewmodel.EditTeaViewModel
import com.tzs.myteaapplication.viewmodel.TeaListViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewTeaTest {
    var teas = mutableListOf<Tea>()
    @Before
    fun before(){
        Tea.currentTea = null
        var t1 = Tea(1)
        t1.name = "T1"
        t1.type = TeaTypes.PUERH
        t1.temp = 1
        t1.amount = 11
        var t2 = Tea(2)
        t2.name = "T2"
        t2.type = TeaTypes.PUERH
        t2.temp = 2
        t2.amount = 22
        var t3 = Tea(3)
        t3.name = "T3"
        t3.type = TeaTypes.PUERH
        t3.temp = 3
        t3.amount = 33
        teas.add(t1)
        teas.add(t2)
        teas.add(t3)
    }

    @Test
    fun createNewTeaObject() {

        val repo = FakeTeaRepository(teas)

        val listViewModel = EditTeaViewModel(repo, ApplicationProvider.getApplicationContext())


        listViewModel.currentTea_Name = "Bai Mu Dan"
        listViewModel.currentTea_AmountOfLeaf = "5"
        listViewModel.currentTea_BrewingTemperature = "80"
        listViewModel.currentTea_type = TeaTypes.BLACK

        listViewModel.saveTea()

        Assert.assertEquals("Bai Mu Dan", teas[3].name)
        Assert.assertEquals(80, teas[3].temp)
        Assert.assertEquals(5, teas[3].amount)
        Assert.assertEquals(TeaTypes.BLACK, teas[3].type)
    }

    @Test
    fun prepareUpdateTea(){
        val teas = mutableListOf<Tea>()
        val repo = FakeTeaRepository(teas)

        var t = Tea(2)
        t.name = "T2"
        t.temp = 100
        t.amount = 5
        t.type = TeaTypes.PUERH
        Tea.currentTea = t

        val listViewModel = EditTeaViewModel(repo, ApplicationProvider.getApplicationContext())

        Assert.assertEquals("T2", listViewModel.currentTea_Name)
        Assert.assertEquals("5", listViewModel.currentTea_AmountOfLeaf)
        Assert.assertEquals("100", listViewModel.currentTea_BrewingTemperature)
        Assert.assertEquals(TeaTypes.PUERH, listViewModel.currentTea_type)
    }

    @Test
    fun prepareUpdateTea_Type(){
        val teas = mutableListOf<Tea>()
        val repo = FakeTeaRepository(teas)

        var t = Tea(2)
        t.name = "T2"
        t.temp = 100
        t.amount = 5
        t.type = TeaTypes.PUERH
        Tea.currentTea = t

        val listViewModel = EditTeaViewModel(repo, ApplicationProvider.getApplicationContext())

        Assert.assertEquals(R.id.PUERH, listViewModel.getCheckedViewId())
    }

    @Test
    fun UpdateTeaObject() {


        val repo = FakeTeaRepository(teas)

        val listViewModel = EditTeaViewModel(repo, ApplicationProvider.getApplicationContext())

        var t = Tea(2)
        Tea.currentTea = t

        listViewModel.currentTea_Name = "Bai Mu Dan"
        listViewModel.currentTea_AmountOfLeaf = "5"
        listViewModel.currentTea_BrewingTemperature = "80"
        listViewModel.currentTea_type = TeaTypes.BLACK

        listViewModel.saveTea()

        Assert.assertEquals("Bai Mu Dan", teas[2].name)
        Assert.assertEquals(80, teas[2].temp)
        Assert.assertEquals(5, teas[2].amount)
        Assert.assertEquals(TeaTypes.BLACK, teas[2].type)
    }

    @Test
    fun deleteTeaObject() {

        val repo = FakeTeaRepository(teas)

        var t = Tea(2)
        t.name = "T2"
        t.amount = 5
        t.temp = 100
        t.type = TeaTypes.PUERH
        Tea.currentTea = t

        val ViewModel = EditTeaViewModel(repo, ApplicationProvider.getApplicationContext())

        ViewModel.deleteTea()

        Assert.assertEquals(2, teas.count())
    }
}