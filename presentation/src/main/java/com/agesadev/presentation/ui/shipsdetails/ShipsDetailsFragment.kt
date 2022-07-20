package com.agesadev.presentation.ui.shipsdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agesadev.presentation.R

class ShipsDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ShipsDetailsFragment()
    }

    private lateinit var viewModel: ShipsDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ships_dertails, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShipsDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}