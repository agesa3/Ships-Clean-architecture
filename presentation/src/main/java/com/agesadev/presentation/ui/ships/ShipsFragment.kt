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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agesadev.domain.model.Ships
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.FragmentShipsBinding
import com.agesadev.presentation.ui.shipsdetails.ShipsDetailsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShipsFragment : Fragment(), ItemOnClick {

    private var _binding: FragmentShipsBinding? = null
    private val binding get() = _binding

    private val modalBottomSheet = ShipsDetailsFragment()


    private lateinit var shipsAdapter: ShipsAdapter
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
        setUpRecyclerView()
        getAndObserveShips()
    }

    private fun setUpRecyclerView() {
        shipsAdapter = ShipsAdapter(this)
        binding?.shipsRecylerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shipsAdapter
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun getAndObserveShips() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                shipsViewModel.ships.collectLatest { state ->
                    when {
                        state.data.isNotEmpty() -> {
                            shipsAdapter.submitList(state.data)
                            hideProgressBar()

                        }
                        state.isLoading -> {
                            showProgressBar()
                        }
                        else -> {
                            hideProgressBar()
//                            show snackbar with retry button
                            Snackbar.make(
                                binding?.root!!,
                                "Error loading ships",
                                Snackbar.LENGTH_LONG
                            ).setAction("Retry") {
                                doRefresh()
                            }.show()
                        }
                    }

                }
            }
        }
    }

    private fun doRefresh() {
        shipsViewModel.refresh()
        getAndObserveShips()
    }

    private fun hideProgressBar() {
        binding?.progressBar?.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(ship: Ships) {
        val actions = ShipsFragmentDirections.actionShipsFragmentToShipsDetailsFragment(ship)
        findNavController().navigate(actions)
    }

}

