package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.tzs.myteaapplication.Model.Tea
import com.tzs.myteaapplication.databinding.FragmentViewTeaBinding


class ViewTeaFragment : Fragment() {

    var currentTeaId = 1
    var currentTea: Tea? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        initTea()
        val args = ViewTeaFragmentArgs.fromBundle(arguments!!)

        val binding = DataBindingUtil.inflate<FragmentViewTeaBinding>(inflater, R.layout.fragment_view_tea, container, false)
        binding.viewedTea = args.currentTeaID.toString()

        setHasOptionsMenu(true)
        return binding.root
    }

    fun initTea(){


        (activity as MainActivity?)?.currentTea = Tea(10)
        (activity as MainActivity?)?.currentTea?.name = "Keemun"

        // fetch tea from db
        // ==========================================================
        // ==========================================================
        currentTea  = (activity as MainActivity?)?.currentTea
        (activity as AppCompatActivity).supportActionBar?.title =  currentTea?.name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.view_tea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        /// put ViewTea fragment into the stack

        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}