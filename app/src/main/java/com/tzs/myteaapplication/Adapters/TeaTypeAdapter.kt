package com.tzs.myteaapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tzs.myteaapplication.models.TeaTypes
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TeaItemViewHolder
import com.tzs.myteaapplication.TeaTypeViewHolder

class TeaTypeAdapter: RecyclerView.Adapter<TeaTypeViewHolder>() {
    var data = listOf<TeaTypes>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TeaTypeViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeaTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.tea_type_radio, parent, false) as TextView
        return TeaTypeViewHolder(view)
    }
}