package com.tzs.myteaapplication.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TeaItemViewHolder
import com.tzs.myteaapplication.database.Tea

class TeaListItemAdapter: RecyclerView.Adapter<TeaItemViewHolder>() {
    var data = listOf<Tea>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TeaItemViewHolder, position: Int) {
        val item = data[position]
        // if(item black tea) kell else ág
       // holder.textView.setTextColor(Color.CYAN)
        holder.textView.text = item.name
        holder.textView.setBackgroundColor(6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeaItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.tea_button, parent, false) as TextView
        return TeaItemViewHolder(view)
    }
}

