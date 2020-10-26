package com.tzs.myteaapplication.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeaViewModelTest {
    @Test
    fun setViewedTea(){
        var t = Tea(1)
        t.name = "name"
        t.type = TeaTypes.GREEN
        t.temp=100
        t.amount=5

        Tea.currentTea = t

        var vm = TeaViewModel(1)


        Assert.assertEquals(t.amount.toString() + "g/100ml", vm.currentTea_AmountOfLeaf)
        Assert.assertEquals(t.temp.toString() + "C°", vm.currentTea_BrewingTemperature)
    }
}