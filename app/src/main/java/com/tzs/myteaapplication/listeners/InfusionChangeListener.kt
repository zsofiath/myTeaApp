package com.tzs.myteaapplication.listeners

import com.tzs.myteaapplication.models.Tea

class InfusionChangeListener (val changeListener: ( )-> Unit){
    fun onChange() = changeListener()
}