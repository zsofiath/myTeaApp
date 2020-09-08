package com.tzs.myteaapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tzs.myteaapplication.databinding.FragmentTeaListBinding
import com.tzs.myteaapplication.databinding.FragmentViewTeaBinding


class ViewTeaFragment : Fragment() {

    var currentTeaId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val args = ViewTeaFragmentArgs.fromBundle(arguments!!)

        val binding = DataBindingUtil.inflate<FragmentViewTeaBinding>(inflater, R.layout.fragment_view_tea, container, false)
        binding.viewedTea = args.currentTeaID.toString()

        return binding.root
    }


}