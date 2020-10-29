package com.tzs.myteaapplication

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tzs.myteaapplication.Adapters.InfusionEditoradapter
import com.tzs.myteaapplication.Adapters.TeaListItemAdapter
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.models.TeaTypes
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.databinding.FragmentEditTeaBinding
import com.tzs.myteaapplication.listeners.InfusionChangeListener
import com.tzs.myteaapplication.listeners.TeaItemClickListener
import com.tzs.myteaapplication.repository.TeaRepository
import com.tzs.myteaapplication.viewmodel.EditTeaViewModel
import com.tzs.myteaapplication.viewmodel.EditTeaViewModelFactory
import com.tzs.myteaapplication.viewmodel.TeaListViewModel
import com.tzs.myteaapplication.viewmodel.TeaListViewModelFactory

class NewTeaFragment : Fragment() {
    var currentTea: Tea? = null
    private lateinit var viewModel: EditTeaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setFragmentTitle("Create new tea")
        val binding = getBindingObjectWithLayoutInflate(inflater, container)

        viewModel = createViewModel()
        binding.editTeaViewModel = viewModel


        val adapter = InfusionEditoradapter(InfusionChangeListener {
            
            Log.i("-----", "----------")
        })

        binding.teaList.adapter = adapter
        listenInfusionListChanges(adapter, binding)

        adNewRowButtonClick(binding)


        hideDeleteButton(binding)
        radioChange(binding)
        setBindings(binding)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun adNewRowButtonClick(binding: FragmentEditTeaBinding) {
        binding.addRowButton.setOnClickListener {
            viewModel.addNewInfusion()
        }
    }

    private fun listenInfusionListChanges(adapter: InfusionEditoradapter, binding: FragmentEditTeaBinding) {
        binding.teaList.adapter = adapter

        viewModel.infusions.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it

            }
        })
    }

    private fun hideDeleteButton(binding: FragmentEditTeaBinding) {
        binding.deleteTea.visibility = View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_tea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.toString() == "Save tea") {
            viewModel.saveTea {
                findNavController().navigate(NewTeaFragmentDirections.actionNewTeaFragment2ToViewTeaFragment(0))
                Toast.makeText(context, "Tea saved: "+viewModel.currentTea_Name, Toast.LENGTH_LONG).show()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun getBindingObjectWithLayoutInflate(inflater:LayoutInflater, container: ViewGroup?): FragmentEditTeaBinding {
        return DataBindingUtil.inflate<FragmentEditTeaBinding>(inflater, R.layout.fragment_edit_tea, container, false)
    }

    private fun setFragmentTitle(title: String?) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    private fun setBindings(binding: FragmentEditTeaBinding){
        binding.viewedTea = Tea.currentTea?.name
    }

    private fun radioChange(binding: FragmentEditTeaBinding) {
        binding.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            viewModel.onTypeSelected(checkedId)
        })
    }

    private fun createViewModel() :EditTeaViewModel{
        val application = requireNotNull(this.activity).application
        val datasource = AppDatabase.getInstance(application).teaDatabaseDao
        var repository = TeaRepository(datasource)
        val viewModelFactory = EditTeaViewModelFactory(repository, application)
        return ViewModelProviders.of(this, viewModelFactory).get(EditTeaViewModel::class.java)
    }
}