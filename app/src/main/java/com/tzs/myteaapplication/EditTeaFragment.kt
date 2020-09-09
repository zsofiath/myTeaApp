package com.tzs.myteaapplication

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.tzs.myteaapplication.databinding.FragmentViewTeaBinding


class EditTeaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentViewTeaBinding>(inflater, R.layout.fragment_view_tea, container, false)
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


}