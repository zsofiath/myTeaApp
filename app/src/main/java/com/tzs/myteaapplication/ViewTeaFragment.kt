package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.viewmodel.TeaViewModel
import com.tzs.myteaapplication.viewmodel.TeaViewModelFactory
import com.tzs.myteaapplication.databinding.FragmentViewTeaBinding


class ViewTeaFragment : Fragment() {

    var currentTeaId = 1
    var currentTea: Tea? = null

    private lateinit var viewModel: TeaViewModel
    private lateinit var viewModelFactory: TeaViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setFragmentTitle(Tea.currentTea?.name)
        val args = getArgumantsFromBundle()

        val binding = getBindingObjectWithLayoutInflate(inflater, container)
        setViewedTea(binding, args)

        viewModel = getViewModel()
        binding.teaViewModel = viewModel
        binding.setLifecycleOwner(this)


        setHasOptionsMenu(true)
        return binding.root
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
    private fun setButtonNumber(binding: FragmentViewTeaBinding, newValue: Int){
        binding.countDown = newValue.toString()
    }

    private fun getViewModel(): TeaViewModel {
        viewModelFactory =
            TeaViewModelFactory(10)
        return ViewModelProviders.of(this, viewModelFactory).get(TeaViewModel::class.java)
    }

    private fun setFragmentTitle(title: String?) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

}