package com.agesadev.presentation.ui.ships

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.FragmentShipsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipsFragment : Fragment() {

    private var _binding: FragmentShipsBinding? = null
    private val binding get() = _binding


    private val shipsViewModel: ShipsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ships, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}