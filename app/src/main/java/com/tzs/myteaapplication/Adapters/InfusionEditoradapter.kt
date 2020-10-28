package com.tzs.myteaapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tzs.myteaapplication.InfusionViewHolder
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TextItemViewHolder

class InfusionEditoradapter(): RecyclerView.Adapter<InfusionViewHolder>() {
    var data = listOf<Int>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: InfusionViewHolder, position: Int) {
        var item = data[position]
        holder.textView.text = item.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfusionViewHolder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.input_item_view, parent, false) as TextView

        return InfusionViewHolder(view)
    }
}