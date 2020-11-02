package com.tzs.myteaapplication.models

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.apache.tools.ant.taskdefs.WaitFor.ONE_SECOND
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfusionTest {
    @Test fun infusion(){
        var inf = Infusion(0)

        Assert.assertEquals("0", inf.visibleValue)

    }

    @Test fun infusion_VisibleValueGetter(){
        var inf = Infusion(0)
        inf.value = 20

        Assert.assertEquals("20", inf.visibleValue)

    }
    @Test fun infusion_ValueGetter(){
        var inf = Infusion(0)
        inf.visibleValue = "20"

        Assert.assertEquals(20, inf.value)

    }
    @Test fun infusion_ValueSetter(){
        var inf = Infusion(0)
        inf.value = 20

        Assert.assertEquals("20", inf.visibleValue)

    }

    @Test fun infusion_VisibleValueSetter(){
        var inf = Infusion(0)
        inf.visibleValue = "100"

        Assert.assertEquals(100, inf.value)
    }
}