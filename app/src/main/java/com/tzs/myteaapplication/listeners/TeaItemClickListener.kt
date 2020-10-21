package com.tzs.myteaapplication.listeners

import com.tzs.myteaapplication.models.Tea

class TeaItemClickListener (val clickListener: (teaId: Int )-> Unit){
    fun onClick(tea: Tea) = clickListener(tea.id)
}

