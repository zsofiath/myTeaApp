package com.tzs.myteaapplication

import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import java.lang.Exception

@BindingAdapter("teaFormatter")
fun Button.setTeaFormatter(item: Tea?) {
    item?.let {
        text = item.name
    }
    item?.let {
        when(item.type) {
            TeaTypes.BLACK -> setBackgroundColor(getContext().getResources().getColor(R.color.BLACK));
            TeaTypes.WHITE -> setBackgroundColor(getContext().getResources().getColor(R.color.WHITE));
            TeaTypes.YELLOW -> setBackgroundColor(getContext().getResources().getColor(R.color.YELLOW));
            TeaTypes.HERBAL -> setBackgroundColor(getContext().getResources().getColor(R.color.HERBAL));
            TeaTypes.PUERH -> setBackgroundColor(getContext().getResources().getColor(R.color.PUERH));
            TeaTypes.OOLONG -> setBackgroundColor(getContext().getResources().getColor(R.color.OOLONG));
            TeaTypes.GREEN -> setBackgroundColor(getContext().getResources().getColor(R.color.GREEN));
            else -> {
                setBackgroundColor(getContext().getResources().getColor(R.color.def));
            }
        }
    }
}