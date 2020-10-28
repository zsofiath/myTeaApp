package com.tzs.myteaapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TextItemViewHolder

class InfusionEditoradapter(): RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Int>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        var item = data[position]
        holder.textView.text = item.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}