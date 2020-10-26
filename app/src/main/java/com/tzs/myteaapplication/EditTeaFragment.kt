package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tzs.myteaapplication.models.Tea
import com.tzs.myteaapplication.databinding.FragmentEditTeaBinding


class EditTeaFragment : Fragment() {

    var currentTea: Tea? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFragmentTitle("Edit "+Tea.currentTea?.name)
        val binding = getBindingObjectWithLayoutInflate(inflater, container)

        setBindings(binding)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_tea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.title == "save Tea") {
            Toast.makeText(context, "Tea saved", Toast.LENGTH_LONG).show()
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

}