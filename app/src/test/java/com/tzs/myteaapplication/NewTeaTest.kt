package com.tzs.myteaapplication

import com.tzs.myteaapplication.Model.Tea
import com.tzs.myteaapplication.Model.TeaTypes
import org.junit.Assert
import org.junit.Test

class NewTeaTest {
    class ExampleUnitTest {
        @Test
        fun createNewTeaObject() {

            var tea = Tea(0)
            tea.name = "Bai Mu Dan"
            tea.type = TeaTypes.BLACK;

            Assert.assertEquals(tea.name, "Bai Mu Dan")
            Assert.assertEquals(tea.type, TeaTypes.BLACK)
        }
    }
}