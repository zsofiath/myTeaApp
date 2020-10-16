package com.tzs.myteaapplication

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tzs.myteaapplication.Model.Tea
import com.tzs.myteaapplication.database.AppDatabase
import com.tzs.myteaapplication.databinding.FragmentEditTeaBinding
import com.tzs.myteaapplication.viewmodel.EditTeaViewModel
import com.tzs.myteaapplication.viewmodel.EditTeaViewModelFactory
import com.tzs.myteaapplication.viewmodel.TeaListViewModel
import com.tzs.myteaapplication.viewmodel.TeaListViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewTeaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTeaFragment : Fragment() {
    var currentTea: Tea? = null
    private lateinit var viewModel: EditTeaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setFragmentTitle()
        val binding = getBindingObjectWithLayoutInflate(inflater, container)
        viewModel = createViewModel()
        binding.editTeaViewModel = viewModel

        setBindings(binding)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setFragmentTitle(){
        currentTea = (activity as MainActivity?)?.currentTea

        setFragmentTitle("Create new tea")
    }

    private fun createViewModel() :EditTeaViewModel{
        val application = requireNotNull(this.activity).application
        val datasource = AppDatabase.getInstance(application).teaDatabaseDao
        val viewModelFactory = EditTeaViewModelFactory(datasource, application)
        return ViewModelProviders.of(this, viewModelFactory).get(EditTeaViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_tea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.toString() == "Save tea") {
            Toast.makeText(context, "Tea saved: "+viewModel.currentTea.name, Toast.LENGTH_LONG).show()
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
        binding.viewedTea = "Edit "+(activity as MainActivity?)?.currentTea?.name
    }
}