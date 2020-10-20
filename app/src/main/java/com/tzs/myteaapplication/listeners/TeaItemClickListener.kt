package com.tzs.myteaapplication.listeners

import com.tzs.myteaapplication.database.Tea

class TeaItemClickListener (val clickListener: (teaId: Int )-> Unit){
    fun onClick(tea: Tea) = clickListener(tea.teaId)
}

