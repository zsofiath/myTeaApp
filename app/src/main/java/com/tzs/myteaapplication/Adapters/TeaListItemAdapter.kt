package com.tzs.myteaapplication.Adapters

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.tzs.myteaapplication.database.Tea
import com.tzs.myteaapplication.databinding.TeaListButtonBinding

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

    class ViewHolder private constructor(val binding: TeaListButtonBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Tea
        ) {
            val res = itemView.context.resources
            var z = res
            // if(item black tea) kell else ág
            // holder.textView.setTextColor(Color.CYAN)
            binding.teaListButton.text = item.name
            //holder.btn.setBackgroundColor(6)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)//.inflate(R.layout.tea_list_button, parent, false)
                val binding = TeaListButtonBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }


}



