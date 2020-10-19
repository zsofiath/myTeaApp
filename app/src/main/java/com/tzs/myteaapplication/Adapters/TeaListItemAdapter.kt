package com.tzs.myteaapplication.Adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import com.tzs.myteaapplication.R
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
        holder.bind(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val btn: Button = itemView.findViewById(R.id.tea_list_button)
        val res = itemView.context.resources
        fun bind(
            item: Tea
        ) {
            var z = res
            // if(item black tea) kell else ág
            // holder.textView.setTextColor(Color.CYAN)
            btn.text = item.name
            //holder.btn.setBackgroundColor(6)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.tea_button, parent, false)

                return ViewHolder(view)
            }
        }
    }


}



