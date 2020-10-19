package com.tzs.myteaapplication

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tzs.myteaapplication.database.Tea

class TeaItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
class TeaTypeViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

@BindingAdapter("teaFormatter")
fun Button.setTeaFormatter(item: Tea?) {
    item?.let {
        text = item.name
    }
}