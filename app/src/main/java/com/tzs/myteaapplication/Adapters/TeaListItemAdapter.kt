package com.tzs.myteaapplication.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TextItemViewHolder
import com.tzs.myteaapplication.database.Tea
import kotlinx.android.synthetic.main.fragment_tea_list.view.*
import kotlinx.android.synthetic.main.fragment_view_tea.view.*

class TeaListItemAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Tea>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        // if(item black tea) kell else ág
       // holder.textView.setTextColor(Color.CYAN)
        holder.textView.text = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.tea_button, parent, false) as TextView
        return TextItemViewHolder(view)
    }
}

