package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.tzs.myteaapplication.Model.Tea
import com.tzs.myteaapplication.ViewModels.TeaListViewModel
import com.tzs.myteaapplication.ViewModels.TeaViewModel
import com.tzs.myteaapplication.databinding.FragmentTeaListBinding
import com.tzs.myteaapplication.databinding.FragmentViewTeaBinding


class ViewTeaFragment : Fragment() {

    var currentTeaId = 1
    var currentTea: Tea? = null

    private lateinit var viewModel: TeaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        initTea()
        val args = getArgumantsFromBundle()

        val binding = getBindingObjectWithLayoutInflate(inflater, container)
        setViewedTea(binding, args)

        viewModel = getViewModel()

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

    private fun getArgumantsFromBundle(): ViewTeaFragmentArgs {
        return ViewTeaFragmentArgs.fromBundle(arguments!!)
    }

    private fun getBindingObjectWithLayoutInflate(inflater:LayoutInflater, container: ViewGroup?): FragmentViewTeaBinding {
        return DataBindingUtil.inflate<FragmentViewTeaBinding>(inflater, R.layout.fragment_view_tea, container, false)
    }

    private fun setViewedTea(binding: FragmentViewTeaBinding, args: ViewTeaFragmentArgs){
        binding.viewedTea = args.currentTeaID.toString()
    }

    private fun getViewModel(): TeaViewModel{
        return ViewModelProviders.of(this).get(TeaViewModel::class.java)
    }


}