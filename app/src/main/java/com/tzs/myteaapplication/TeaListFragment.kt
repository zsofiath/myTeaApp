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
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.repository.TeaRepository
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
        clickAddNewTea(binding)

        val adapter = setTeaListItemAdapter(binding, viewModel)

        notifyTeaListChanges(adapter)

        return binding.root
    }

    private fun clickAddNewTea(binding: FragmentTeaListBinding) {
        binding.addNewTea.setOnClickListener{ v: View ->
            v.findNavController().navigate(R.id.action_teaListFragment_to_newTeaFragment2)
        }
    }

    private fun setTeaListItemAdapter(binding: FragmentTeaListBinding, viewModel: TeaListViewModel): TeaListItemAdapter {
        val adapter = TeaListItemAdapter(TeaItemClickListener { teaId ->
            fetchTeaAndNavigate(teaId)
        })

        binding.teaList.adapter = adapter
        return adapter
    }

    private fun fetchTeaAndNavigate(teaId: Int){
        viewModel.fetchTea(teaId).observe(viewLifecycleOwner, Observer {
            it?.let {
                Tea.currentTea = it
                findNavController().navigate(TeaListFragmentDirections.actionTeaListFragmentToViewTeaFragment(teaId))
            }
        })
    }

    private fun createViewModel() :TeaListViewModel{
        val application = requireNotNull(this.activity).application
        val datasource = AppDatabase.getInstance(application).teaDatabaseDao
        var repository = TeaRepository(datasource)
        val viewModelFactory = TeaListViewModelFactory(repository, application)
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
        Tea.currentTea = null
    }

    private fun getBindingObjectWithLayoutInflate(inflater:LayoutInflater, container: ViewGroup?): FragmentTeaListBinding{
        return DataBindingUtil.inflate<FragmentTeaListBinding>(inflater, R.layout.fragment_tea_list, container, false)
    }
}