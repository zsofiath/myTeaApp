package com.tzs.myteaapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tzs.myteaapplication.InfusionViewHolder
import com.tzs.myteaapplication.R
import com.tzs.myteaapplication.TextItemViewHolder
import com.tzs.myteaapplication.databinding.InputItemViewBinding
import com.tzs.myteaapplication.databinding.TeaListButtonBinding
import com.tzs.myteaapplication.listeners.InfusionChangeListener
import com.tzs.myteaapplication.listeners.TeaItemClickListener
import com.tzs.myteaapplication.models.Tea

class InfusionEditoradapter(val changeListener: InfusionChangeListener): RecyclerView.Adapter<InfusionEditoradapter.ViewHolder>() {
    var data = listOf<Int>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: InfusionEditoradapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, changeListener)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfusionEditoradapter.ViewHolder {
        return InfusionEditoradapter.ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: InputItemViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: Int,
            clickListener: InfusionChangeListener
        ) {
            //binding.tea = item
            binding.infusionChangeListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): InfusionEditoradapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)//.inflate(R.layout.tea_list_button, parent, false)
                val binding = InputItemViewBinding.inflate(layoutInflater, parent, false)

                return InfusionEditoradapter.ViewHolder(binding)
            }
        }
    }
}