package com.tzs.myteaapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tzs.myteaapplication.databinding.FragmentTeaListBinding


class TeaListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTeaListBinding>(inflater, R.layout.fragment_tea_list, container, false)
        return binding.root
    }


}