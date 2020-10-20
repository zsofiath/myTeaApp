package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.tzs.myteaapplication.Adapters.TeaListItemAdapter
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.viewmodel.TeaListViewModel
import com.tzs.myteaapplication.databinding.FragmentTeaListBinding
import com.tzs.myteaapplication.listeners.TeaItemClickListener
import com.tzs.myteaapplication.viewmodel.TeaListViewModelFactory


class TeaListFragment : Fragment() {

    private lateinit var viewModel: TeaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFragmentTitle()
        clearCurrentTea()

        val binding = getBindingObjectWithLayoutInflate(inflater, container)
        viewModel = createViewModel()
        binding.teaListViewModel = viewModel

        val adapter = setTeaListItemAdapter(binding)

        notifyTeaListChanges(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setTeaListItemAdapter(binding: FragmentTeaListBinding): TeaListItemAdapter {
        val adapter = TeaListItemAdapter(TeaItemClickListener { teaId ->
            findNavController().navigate(TeaListFragmentDirections.actionTeaListFragmentToViewTeaFragment(teaId))
        })

        binding.teaList.adapter = adapter
        return adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tea_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun createViewModel() :TeaListViewModel{
        val application = requireNotNull(this.activity).application
        val datasource = AppDatabase.getInstance(application).teaDatabaseDao
        val viewModelFactory = TeaListViewModelFactory(datasource, application)
        return ViewModelProviders.of(this, viewModelFactory).get(TeaListViewModel::class.java)
    }

    private fun setFragmentTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = "Teas"
    }

    private fun notifyTeaListChanges(adapter: TeaListItemAdapter){
        viewModel.teas_liveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data =  it
            }
        })
    }

    private fun clearCurrentTea(){
        (activity as MainActivity?)?.currentTea = null
    }

    private fun getBindingObjectWithLayoutInflate(inflater:LayoutInflater, container: ViewGroup?): FragmentTeaListBinding{
        return DataBindingUtil.inflate<FragmentTeaListBinding>(inflater, R.layout.fragment_tea_list, container, false)
    }
}