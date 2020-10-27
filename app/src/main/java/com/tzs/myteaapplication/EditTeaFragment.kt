package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.databinding.FragmentEditTeaBinding
import com.tzs.myteaapplication.repository.TeaRepository
import com.tzs.myteaapplication.viewmodel.EditTeaViewModel
import com.tzs.myteaapplication.viewmodel.EditTeaViewModelFactory


class EditTeaFragment : Fragment() {

    var currentTea: Tea? = null
    private lateinit var viewModel: EditTeaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFragmentTitle("Edit "+Tea.currentTea?.name)
        val binding = getBindingObjectWithLayoutInflate(inflater, container)

        viewModel = createViewModel()
        binding.editTeaViewModel = viewModel
        binding.radioGroup.check(viewModel.getCheckedViewId())
        radioChange(binding)
        binding.deleteTea.setOnClickListener { v:View ->
            viewModel.deleteTea {
                v.findNavController().navigate(R.id.action_editTeaFragment_to_teaListFragment)
            }
        }

        setBindings(binding)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_tea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.title == "Save tea") {
            viewModel.saveTea{}
            Toast.makeText(context, "Tea updated", Toast.LENGTH_LONG).show()
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
        //binding.viewedTea = "Edit "+(activity as MainActivity?)?.currentTea?.name
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