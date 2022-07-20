package com.agesadev.presentation.ui.ships

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.FragmentShipsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShipsFragment : Fragment() {

    private var _binding: FragmentShipsBinding? = null
    private val binding get() = _binding


    private val shipsViewModel: ShipsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShipsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getAndObserveShips()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getAndObserveShips()

    }


    private fun getAndObserveShips() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                shipsViewModel.ships.collectLatest { state ->
                    when {
                        state.data.isNotEmpty() -> {
                            Log.d("Wow", "getAndObserveTips:  ${state.data}")
//                            homeTipsAdapter.submitList(state.data)
//                            hideProgressBar()

                        }
                        state.isLoading -> {
//                            showProgressBar()
                            Log.d("Wow", "Loading .....")
                        }
                        else -> {
//                            showError(state.error)
                            Log.d("Wow", "Error .....${state.error}")
                        }
//                        state.error -> {
////                            hideProgressBar()
////                            showSnackBar()
////                            showNetworkFailImage()
//                        }
                    }

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}