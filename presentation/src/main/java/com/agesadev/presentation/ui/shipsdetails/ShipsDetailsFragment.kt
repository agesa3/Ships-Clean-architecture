package com.agesadev.presentation.ui.shipsdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.FragmentShipsDertailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShipsDetailsFragment : BottomSheetDialogFragment() {


    private lateinit var viewModel: ShipsDetailsViewModel
    private var _binding: FragmentShipsDertailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShipsDertailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ShipsDetailsViewModel::class.java]

    }

}