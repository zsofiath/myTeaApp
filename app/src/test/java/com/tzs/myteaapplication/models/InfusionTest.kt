package com.tzs.myteaapplication.models

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfusionTest {
    @Test fun infusion(){
        var inf = Infusion()
        inf.value = 0

        Assert.assertEquals("0", inf.visibleValue)

    }

    @Test fun infusion_VisibleValueGetter(){
        var inf = Infusion()
        inf.value = 20

        Assert.assertEquals("20", inf.visibleValue)

    }
    @Test fun infusion_ValueGetter(){
        var inf = Infusion()
        inf.visibleValue = "20"

        Assert.assertEquals(20, inf.value)

    }
    @Test fun infusion_ValueSetter(){
        var inf = Infusion()
        inf.value = 20

        Assert.assertEquals("20", inf.visibleValue)

    }

    @Test fun infusion_VisibleValueSetter(){
        var inf = Infusion()
        inf.visibleValue = "100"

        Assert.assertEquals(100, inf.value)

    }
}