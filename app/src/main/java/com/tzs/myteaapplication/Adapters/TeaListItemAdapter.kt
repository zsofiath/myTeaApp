package com.tzs.myteaapplication.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TeaItemViewHolder
import com.tzs.myteaapplication.database.Tea

class TeaListItemAdapter: RecyclerView.Adapter<TeaListItemAdapter.ViewHolder>() {
    var data = listOf<Tea>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // if(item black tea) kell else ág
       // holder.textView.setTextColor(Color.CYAN)
        holder.btn.text = item.name
        //holder.btn.setBackgroundColor(6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.tea_button, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val btn: Button = itemView.findViewById(R.id.tea_list_button)
    }
}



